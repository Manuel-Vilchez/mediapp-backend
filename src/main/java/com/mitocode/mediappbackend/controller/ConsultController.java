package com.mitocode.mediappbackend.controller;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mitocode.mediappbackend.dto.*;
import com.mitocode.mediappbackend.model.Consult;
import com.mitocode.mediappbackend.model.Exam;
import com.mitocode.mediappbackend.model.MediaFile;
import com.mitocode.mediappbackend.service.IConsultService;
import com.mitocode.mediappbackend.service.IMediaFileService;
import com.mitocode.mediappbackend.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {

    private final IConsultService service;
    private final IMediaFileService mfService;
    private final Cloudinary cloudinary;

    /*@Qualifier("consultMapper")
    private final ModelMapper modelMapper;*/

    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() {
        //List<ConsultDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        List<ConsultDTO> list = mapperUtil.mapList(service.findAll(), ConsultDTO.class, "consultMapper");

        return ResponseEntity.ok(list);

        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) {
        Consult obj = service.findById(id);

        return ResponseEntity.ok(mapperUtil.map(obj, ConsultDTO.class, "consultMapper"));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO dto) {
        Consult obj = service.saveTransactional(mapperUtil.map(dto.getConsult(), Consult.class, "consultMapper"), mapperUtil.mapList(dto.getLstExam(), Exam.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ConsultDTO dto) {
        dto.setIdConsult(id);
        Consult obj = service.update(id, mapperUtil.map(dto, Consult.class, "consultMapper"));

        return ResponseEntity.ok(mapperUtil.map(obj, ConsultDTO.class, "consultMapper"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    ////////Queries///////////
    @PostMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> searchByOthers(@RequestBody FilterConsultDTO dto){
        List<Consult> list = service.search(dto.getDni(), dto.getFullname());

        List<ConsultDTO> listDTO = mapperUtil.mapList(list, ConsultDTO.class, "consultMapper");

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/search/dates")
    public ResponseEntity<List<ConsultDTO>> searchByDates(
            @RequestParam(value = "date1", defaultValue = "2024-08-01") String date1,
            @RequestParam(value = "date2", defaultValue = "2024-08-15") String date2
    ){
        List<Consult> list = service.searchByDates(LocalDateTime.parse(date1), LocalDateTime.parse(date2));

        List<ConsultDTO> listDTO = mapperUtil.mapList(list, ConsultDTO.class,"consultMapper" );

        return ResponseEntity.ok(listDTO);

    }

    @GetMapping("/callProcedureNative")
    public ResponseEntity<List<ConsultProcDTO>> callProcedureNative(){
        return ResponseEntity.ok(service.callProcedureOrFunctionNative());
    }

    @GetMapping("/callProcedureProjection")
    public ResponseEntity<List<IConsultProcDTO>> callProcedureProjection(){
        return ResponseEntity.ok(service.callProcedureOrFunctionProjection());
    }

    @GetMapping(value = "/generateReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateReport() throws Exception {
        byte[] data = service.generateReport();

        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "readFile/{idFile}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> readFile(@PathVariable("idFile") Integer idFile) throws Exception {
        byte[] data = mfService.findById(idFile).getContent();
        return ResponseEntity.ok(data);
    }

    @PostMapping(value = "/saveFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        //DB
        MediaFile mf = new MediaFile();
        mf.setContent(multipartFile.getBytes());
        mf.setFileName(multipartFile.getOriginalFilename());
        mf.setFileType(multipartFile.getContentType());

        mfService.save(mf);


        //Repo Externo / Cloudinary
        /*File f = this.converToFile(multipartFile);
        Map<String, Object> response = cloudinary.uploader().upload(f, ObjectUtils.asMap("resource_type", "auto"));
        JSONObject json = new JSONObject(response);
        String url = json.getString("url");
        System.out.println(url);*/

        return ResponseEntity.ok().build();

    }

    private File converToFile(MultipartFile multipartFile) throws Exception {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return file;
    }





    /*private ConsultDTO convertToDto(Consult obj) {
        return modelMapper.map(obj, ConsultDTO.class);
    }

    private Consult convertToEntity(ConsultDTO dto) {
        return modelMapper.map(dto, Consult.class);
    }*/









    //@Autowired
    //private final ConsultServiceImpl service;

    /*public ConsultController(ConsultService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Consult saveConsult() {
        return service.validAndSave(new Consult());
    }*/
}
