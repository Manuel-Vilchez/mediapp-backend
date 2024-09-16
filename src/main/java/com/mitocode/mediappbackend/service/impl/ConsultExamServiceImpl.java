package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.ConsultExam;
import com.mitocode.mediappbackend.model.Exam;
import com.mitocode.mediappbackend.repo.IConsultExamRepo;
import com.mitocode.mediappbackend.service.IConsultExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultExamServiceImpl implements IConsultExamService {

    private final IConsultExamRepo repo;

    @Override
    public List<Exam> getExamsByConsultId(Integer id) {
        return repo.getExamsByConsultId(id);
    }
}
