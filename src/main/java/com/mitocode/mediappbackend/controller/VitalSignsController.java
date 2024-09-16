package com.mitocode.mediappbackend.controller;


import com.mitocode.mediappbackend.dto.VitalSignDTO;
import com.mitocode.mediappbackend.model.Patient;
import com.mitocode.mediappbackend.model.VitalSign;
import com.mitocode.mediappbackend.service.IPatientService;
import com.mitocode.mediappbackend.service.IVitalSignService;
import com.mitocode.mediappbackend.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/vitalSigns")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class VitalSignsController {

    private final IVitalSignService service;

    /*@Qualifier("defaultMapper")
    private final ModelMapper modelMapper;*/
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<VitalSignDTO>> findAll() {
        //List<VitalSignDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        List<VitalSignDTO> list = mapperUtil.mapList(service.findAll(), VitalSignDTO.class);

        return ResponseEntity.ok(list);

        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignDTO> findById(@PathVariable("id") Integer id) {
        VitalSign obj = service.findById(id);

        return ResponseEntity.ok(mapperUtil.map(obj, VitalSignDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody VitalSignDTO dto) {

        VitalSign obj = service.save(mapperUtil.map(dto, VitalSign.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getVitalSignId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSignDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody VitalSignDTO dto) {
        dto.setVitalSignId(id);
        VitalSign obj = service.update(id, mapperUtil.map(dto, VitalSign.class));

        return ResponseEntity.ok(mapperUtil.map(obj, VitalSignDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<VitalSignDTO> findByIdHateoas(@PathVariable("id") Integer id) {
        EntityModel<VitalSignDTO> resource = EntityModel.of(mapperUtil.map(service.findById(id), VitalSignDTO.class));

        //generar link informativo

        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(MedicController.class).findAll());
        resource.add(link1.withRel("vitalSign-self-info"));
        resource.add(link2.withRel("all-medic-info"));
        return resource;
    }

    /*@GetMapping("/pageable")
    public ResponseEntity<Page<VitalSignDTO>> listPage(Pageable pageable) {
        Page<VitalSignDTO> page = service.listPage(pageable).map(e -> mapperUtil.map(e, VitalSignDTO.class));

        return ResponseEntity.ok(page);
    }*/

    /*private VitalSignDTO convertToDto(VitalSign obj) {
        return modelMapper.map(obj, VitalSignDTO.class);
    }

    private VitalSign convertToEntity(VitalSignDTO dto) {
        return modelMapper.map(dto, VitalSign.class);
    }*/









    //@Autowired
    //private final VitalSignServiceImpl service;

    /*public VitalSignController(VitalSignService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public VitalSign saveVitalSign() {
        return service.validAndSave(new VitalSign());
    }*/
}
