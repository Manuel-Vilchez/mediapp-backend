package com.mitocode.mediappbackend.controller;


import com.mitocode.mediappbackend.dto.MedicDTO;
import com.mitocode.mediappbackend.model.Medic;
import com.mitocode.mediappbackend.service.IMedicService;
import com.mitocode.mediappbackend.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medics")
@RequiredArgsConstructor
public class MedicController {

    private final IMedicService service;

    /*@Qualifier("medicMapper")
    private final ModelMapper modelMapper;*/

    private final MapperUtil mapperUtil;

    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('USER')")
    @PreAuthorize("@authorizeLogic.hasAccess('findAll')")
    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll() {
        //List<MedicDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        List<MedicDTO> list = mapperUtil.mapList(service.findAll(), MedicDTO.class, "medicMapper");

        return ResponseEntity.ok(list);

        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id) {
        Medic obj = service.findById(id);

        return ResponseEntity.ok(mapperUtil.map(obj, MedicDTO.class, "medicMapper"));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDTO dto) {
        Medic obj = service.save(mapperUtil.map(dto, Medic.class, "medicMapper"));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody MedicDTO dto) {
        dto.setIdMedic(id);
        Medic obj = service.update(id, mapperUtil.map(dto, Medic.class, "medicMapper"));

        return ResponseEntity.ok(mapperUtil.map(obj, MedicDTO.class, "medicMapper"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    /*private MedicDTO convertToDto(Medic obj) {
        return modelMapper.map(obj, MedicDTO.class);
    }

    private Medic convertToEntity(MedicDTO dto) {
        return modelMapper.map(dto, Medic.class);
    }*/









    //@Autowired
    //private final MedicServiceImpl service;

    /*public MedicController(MedicService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Medic saveMedic() {
        return service.validAndSave(new Medic());
    }*/
}
