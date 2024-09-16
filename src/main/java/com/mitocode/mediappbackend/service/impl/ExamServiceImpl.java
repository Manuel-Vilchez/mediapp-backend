package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.Exam;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.IExamRepo;
import com.mitocode.mediappbackend.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {

    private final IExamRepo repo;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }

    /*@Override
    public Exam save(Exam exam) {
        return repo.save(exam);
    }

    @Override
    public Exam update(Integer id, Exam exam) {
        return repo.save(exam);
    }

    @Override
    public List<Exam> findAll() {
        return repo.findAll();
    }

    @Override
    public Exam findById(Integer id) {
        return repo.findById(id).orElse(new Exam());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/


    //@Autowired
    /*private final ExamRepo repo;

    public Exam validAndSave(Exam exam) {
        if (exam.getIdExam() == 0){
            return repo.save(exam);
        } else {
            return new Exam();
        }
    }*/
}
