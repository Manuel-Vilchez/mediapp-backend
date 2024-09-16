package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.Menu;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.IMenuRepo;
import com.mitocode.mediappbackend.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu, Integer> implements IMenuService {

    private final IMenuRepo repo;

    @Override
    protected IGenericRepo<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> getMenusByUsername(String username) {
        //String contextUser = SecurityContextHolder.getContext().getAuthentication().getName();

        return repo.getMenusByUsername(username);
    }

}
