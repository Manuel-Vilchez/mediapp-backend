package com.mitocode.mediappbackend.controller;

import com.mitocode.mediappbackend.dto.MenuDTO;
import com.mitocode.mediappbackend.service.IMenuService;
import com.mitocode.mediappbackend.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService service;

    private final MapperUtil mapperUtil;

    @PostMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser(@RequestBody String username){
        List<MenuDTO> menusDTO = mapperUtil.mapList(service.getMenusByUsername(username), MenuDTO.class);

        return ResponseEntity.ok(menusDTO);

    }
}
