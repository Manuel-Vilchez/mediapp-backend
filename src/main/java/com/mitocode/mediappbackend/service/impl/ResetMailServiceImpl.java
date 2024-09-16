package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.ResetMail;
import com.mitocode.mediappbackend.repo.IResetMailRepo;
import com.mitocode.mediappbackend.service.IResetMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetMailServiceImpl implements IResetMailService {

    private final IResetMailRepo repo;

    @Override
    public ResetMail findByRandom(String random) {
        return repo.findByRandom(random);
    }

    @Override
    public void save(ResetMail resetMail) {
        repo.save(resetMail);
    }

    @Override
    public void delete(ResetMail resetMail) {
        repo.delete(resetMail);
    }
}
