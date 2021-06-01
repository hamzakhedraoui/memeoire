package com.example.wcare.service;

import com.example.wcare.dao.AccountRepository;
import com.example.wcare.dao.CabineRepository;
import com.example.wcare.model.Account;
import com.example.wcare.model.Cabine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CabineService {


    private CabineRepository repo;

    @Autowired
    public CabineService(CabineRepository repo){
        this.repo = repo;
    }

    public List<Cabine> listAll() {
        return repo.findAll();
    }

    public void save(Cabine cabine) {
        repo.save(cabine);
    }

    public Cabine get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
