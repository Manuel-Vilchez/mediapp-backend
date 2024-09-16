package com.mitocode.mediappbackend.service;

import com.mitocode.mediappbackend.model.User;

public interface ILoginService {

    User checkUsername(String username);
    void changePassword(String password, String username);
}
