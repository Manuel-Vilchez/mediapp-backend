package com.mitocode.mediappbackend.service;

import com.mitocode.mediappbackend.model.ResetMail;

public interface IResetMailService {
    ResetMail findByRandom(String random);
    void save(ResetMail resetMail);
    void delete(ResetMail resetMail);
}
