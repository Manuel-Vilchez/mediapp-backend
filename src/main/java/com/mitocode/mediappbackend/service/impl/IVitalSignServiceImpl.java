package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.VitalSign;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.IVitalSignRepo;
import com.mitocode.mediappbackend.service.IVitalSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IVitalSignServiceImpl extends CRUDImpl<VitalSign, Integer> implements IVitalSignService {

    private final IVitalSignRepo repo;

    @Override
    protected IGenericRepo<VitalSign, Integer> getRepo() {
        return repo;
    }
}
