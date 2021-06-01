package com.example.wcare.service;


import com.example.wcare.dao.AccountRepository;
import com.example.wcare.dao.IlnessToCureRepository;
import com.example.wcare.model.Account;
import com.example.wcare.model.IlnessToCure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IlnessToCureService {

    private IlnessToCureRepository repo;

    @Autowired
    public IlnessToCureService(IlnessToCureRepository repo){
        this.repo = repo;
    }

    public List<IlnessToCure> listAll() {
        return repo.findAll();
    }

    public void save(IlnessToCure ilnessToCure) {
        repo.save(ilnessToCure);
    }

    public IlnessToCure get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
