package com.mitocode.mediappbackend.controller;


import com.mitocode.mediappbackend.dto.ExamDTO;
import com.mitocode.mediappbackend.model.Exam;
import com.mitocode.mediappbackend.service.IExamService;
import com.mitocode.mediappbackend.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final IExamService service;

    /*@Qualifier("examMapper")
    private final ModelMapper modelMapper;*/

    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll() {
        //List<ExamDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        List<ExamDTO> list = mapperUtil.mapList(service.findAll(), ExamDTO.class);

        return ResponseEntity.ok(list);

        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id) {
        Exam obj = service.findById(id);

        return ResponseEntity.ok(mapperUtil.map(obj, ExamDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExamDTO dto) {
        Exam obj = service.save(mapperUtil.map(dto, Exam.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExam()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ExamDTO dto) {
        dto.setIdExam(id);
        Exam obj = service.update(id, mapperUtil.map(dto, Exam.class));

        return ResponseEntity.ok(mapperUtil.map(obj, ExamDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    /*private ExamDTO convertToDto(Exam obj) {
        return modelMapper.map(obj, ExamDTO.class);
    }

    private Exam convertToEntity(ExamDTO dto) {
        return modelMapper.map(dto, Exam.class);
    }*/









    //@Autowired
    //private final ExamServiceImpl service;

    /*public ExamController(ExamService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Exam saveExam() {
        return service.validAndSave(new Exam());
    }*/
}
