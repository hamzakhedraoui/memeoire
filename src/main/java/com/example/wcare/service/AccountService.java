package com.example.wcare.service;

import com.example.wcare.dao.AccountRepository;
import com.example.wcare.dao.ProductRepository;
import com.example.wcare.model.Account;
import com.example.wcare.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private AccountRepository repo;

    @Autowired
    public AccountService(AccountRepository repo){
        this.repo = repo;
    }

    public List<Account> listAll() {
        return repo.findAll();
    }

    public void save(Account account) {
        repo.save(account);
    }

    public Account get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
