package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.Speciality;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.ISpecialityRepo;
import com.mitocode.mediappbackend.service.ISpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl extends CRUDImpl<Speciality, Integer> implements ISpecialityService {

    private final ISpecialityRepo repo;

    @Override
    protected IGenericRepo<Speciality, Integer> getRepo() {
        return repo;
    }

    /*@Override
    public Speciality save(Speciality speciality) {
        return repo.save(speciality);
    }

    @Override
    public Speciality update(Integer id, Speciality speciality) {
        return repo.save(speciality);
    }

    @Override
    public List<Speciality> findAll() {
        return repo.findAll();
    }

    @Override
    public Speciality findById(Integer id) {
        return repo.findById(id).orElse(new Speciality());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/


    //@Autowired
    /*private final SpecialityRepo repo;

    public Speciality validAndSave(Speciality speciality) {
        if (speciality.getIdSpeciality() == 0){
            return repo.save(speciality);
        } else {
            return new Speciality();
        }
    }*/
}
