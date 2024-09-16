package com.mitocode.mediappbackend.service;

import com.mitocode.mediappbackend.model.Menu;

import java.util.List;

public interface IMenuService {

    List<Menu> getMenusByUsername(String username);
}
