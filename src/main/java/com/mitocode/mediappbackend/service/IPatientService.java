package com.mitocode.mediappbackend.service;

import com.mitocode.mediappbackend.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPatientService extends ICRUD<Patient, Integer>{

    Page<Patient> listPage(Pageable pageable);

    /*Patient save(Patient patient);
    Patient update(Integer id, Patient patient);
    List<Patient> findAll();
    Patient findById(Integer id);
    void delete(Integer id);*/
}
