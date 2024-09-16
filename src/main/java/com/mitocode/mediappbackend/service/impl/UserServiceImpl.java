package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.User;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.IUserRepo;
import com.mitocode.mediappbackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    private final IUserRepo repo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }

    /*@Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public User update(Integer id, User user) {
        return repo.save(user);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findById(Integer id) {
        return repo.findById(id).orElse(new User());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/


    //@Autowired
    /*private final UserRepo repo;

    public User validAndSave(User user) {
        if (user.getIdUser() == 0){
            return repo.save(user);
        } else {
            return new User();
        }
    }*/
}
