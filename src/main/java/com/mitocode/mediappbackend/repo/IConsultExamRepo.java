package com.mitocode.mediappbackend.repo;

import com.mitocode.mediappbackend.model.ConsultExam;
import com.mitocode.mediappbackend.model.ConsultExamPK;
import com.mitocode.mediappbackend.model.Exam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IConsultExamRepo extends IGenericRepo<ConsultExam, ConsultExamPK>{

    //@Transactional
    @Modifying
    @Query(value = "INSERT INTO consult_exam(id_consult, id_exam) VALUES(:idConsult, :idExam)", nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);

    //@Query("SELECT new com.mitocode.com.model.ConsultExam(ce.exam) FROM ConsultExam ce WHERE ce.consult.idConsult = :idConsult")
    @Query("SELECT ce.exam FROM ConsultExam ce WHERE ce.consult.idConsult = :idConsult")
    List<Exam> getExamsByConsultId(@Param("idConsult") Integer id);

}
