package com.mitocode.mediappbackend.service;

import com.mitocode.mediappbackend.dto.ConsultProcDTO;
import com.mitocode.mediappbackend.dto.IConsultProcDTO;
import com.mitocode.mediappbackend.model.Consult;
import com.mitocode.mediappbackend.model.Exam;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultService extends ICRUD<Consult, Integer>{

    Consult saveTransactional(Consult consult, List<Exam> exams);
    List<Consult> search(String dni, String fullname);
    List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2);

    List<ConsultProcDTO> callProcedureOrFunctionNative();

    List<IConsultProcDTO> callProcedureOrFunctionProjection();
    byte[] generateReport() throws Exception;

}
