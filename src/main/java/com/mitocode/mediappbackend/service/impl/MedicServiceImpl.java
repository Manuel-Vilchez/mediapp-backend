package com.mitocode.mediappbackend.service.impl;

import com.mitocode.mediappbackend.model.Medic;
import com.mitocode.mediappbackend.repo.IGenericRepo;
import com.mitocode.mediappbackend.repo.IMedicRepo;
import com.mitocode.mediappbackend.service.IMedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDImpl<Medic, Integer> implements IMedicService {

    private final IMedicRepo repo;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }

    /*@Override
    public Medic save(Medic medic) {
        return repo.save(medic);
    }

    @Override
    public Medic update(Integer id, Medic medic) {
        return repo.save(medic);
    }

    @Override
    public List<Medic> findAll() {
        return repo.findAll();
    }

    @Override
    public Medic findById(Integer id) {
        return repo.findById(id).orElse(new Medic());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/


    //@Autowired
    /*private final MedicRepo repo;

    public Medic validAndSave(Medic medic) {
        if (medic.getIdMedic() == 0){
            return repo.save(medic);
        } else {
            return new Medic();
        }
    }*/
}
