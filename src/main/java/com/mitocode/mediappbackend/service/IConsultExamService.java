package com.mitocode.mediappbackend.service;

import com.mitocode.mediappbackend.model.Exam;

import java.util.List;

public interface IConsultExamService {

    List<Exam> getExamsByConsultId(Integer id);
}
