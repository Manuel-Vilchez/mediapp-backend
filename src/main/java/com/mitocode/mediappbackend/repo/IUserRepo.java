package com.mitocode.mediappbackend.repo;

import com.mitocode.mediappbackend.model.User;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepo extends IGenericRepo<User, Integer>{

    //@Query("FROM User u WHERE u.username = :username")
    //DerivedQuery
    User findOneByUsername(String username);
}
