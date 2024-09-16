package com.mitocode.mediappbackend.controller;


import com.mitocode.mediappbackend.dto.SpecialityDTO;
import com.mitocode.mediappbackend.model.Speciality;
import com.mitocode.mediappbackend.service.ISpecialityService;
import com.mitocode.mediappbackend.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final ISpecialityService service;

    /*@Qualifier("defaultMapper")
    private final ModelMapper modelMapper;*/

    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> findAll() {
        //List<SpecialityDTO> list = service.findAll().stream().map(this::convertToDto).toList();

        List<SpecialityDTO> list = mapperUtil.mapList(service.findAll(), SpecialityDTO.class);
        return ResponseEntity.ok(list);

        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> findById(@PathVariable("id") Integer id) {
        Speciality obj = service.findById(id);

        return ResponseEntity.ok(mapperUtil.map(obj, SpecialityDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SpecialityDTO dto) {
        Speciality obj = service.save(mapperUtil.map(dto, Speciality.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpeciality()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SpecialityDTO dto) {
        dto.setIdSpeciality(id);
        Speciality obj = service.update(id, mapperUtil.map(dto, Speciality.class));

        return ResponseEntity.ok(mapperUtil.map(obj, SpecialityDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    /*private SpecialityDTO convertToDto(Speciality obj) {
        return modelMapper.map(obj, SpecialityDTO.class);
    }

    private Speciality convertToEntity(SpecialityDTO dto) {
        return modelMapper.map(dto, Speciality.class);
    }*/









    //@Autowired
    //private final SpecialityServiceImpl service;

    /*public SpecialityController(SpecialityService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Speciality saveSpeciality() {
        return service.validAndSave(new Speciality());
    }*/
}
