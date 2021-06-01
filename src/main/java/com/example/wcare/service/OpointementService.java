package com.example.wcare.service;


import com.example.wcare.dao.OponintementRepository;
import com.example.wcare.model.Appointments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpointementService {
    private OponintementRepository repo;

    @Autowired
    public OpointementService(OponintementRepository repo){
        this.repo = repo;
    }

    public List<Appointments> listAll() {
        return repo.findAll();
    }

    public void save(Appointments appointments) {
        repo.save(appointments);
    }

    public Appointments get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
