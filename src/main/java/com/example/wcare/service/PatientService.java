package com.example.wcare.service;


import com.example.wcare.dao.AccountRepository;
import com.example.wcare.dao.PatientRepository;
import com.example.wcare.model.Account;
import com.example.wcare.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService {
    private PatientRepository repo;

    @Autowired
    public PatientService(PatientRepository repo){
        this.repo = repo;
    }

    public List<Patient> listAll() {
        return repo.findAll();
    }

    public void save(Patient patient) {
        repo.save(patient);
    }

    public Patient get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
