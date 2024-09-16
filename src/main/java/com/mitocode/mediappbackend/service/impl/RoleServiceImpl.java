package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.Role;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.IRoleRepo;
import com.mitocode.mediappbackend.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    private final IRoleRepo repo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }

    /*@Override
    public Role save(Role role) {
        return repo.save(role);
    }

    @Override
    public Role update(Integer id, Role role) {
        return repo.save(role);
    }

    @Override
    public List<Role> findAll() {
        return repo.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return repo.findById(id).orElse(new Role());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/


    //@Autowired
    /*private final RoleRepo repo;

    public Role validAndSave(Role role) {
        if (role.getIdRole() == 0){
            return repo.save(role);
        } else {
            return new Role();
        }
    }*/
}
