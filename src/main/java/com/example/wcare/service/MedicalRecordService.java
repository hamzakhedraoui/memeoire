package com.example.wcare.service;

import com.example.wcare.dao.MedicalRecodRepository;
import com.example.wcare.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MedicalRecordService {

    private MedicalRecodRepository repo;

    @Autowired
    public MedicalRecordService(MedicalRecodRepository repo){
        this.repo = repo;
    }

    public List<MedicalRecord> listAll() {
        return repo.findAll();
    }

    public void save(MedicalRecord medicalRecord) {
        repo.save(medicalRecord);
    }

    public MedicalRecord get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
