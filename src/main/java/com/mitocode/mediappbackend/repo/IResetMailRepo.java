package com.mitocode.mediappbackend.repo;

import com.mitocode.mediappbackend.model.ResetMail;

public interface IResetMailRepo extends IGenericRepo<ResetMail, Integer>{

    //FROM ResetMail rm WHERE rm.random = ?
    ResetMail findByRandom(String random);
}
