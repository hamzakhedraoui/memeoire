package com.example.wcare.service;


import com.example.wcare.dao.ProductRepository;
import com.example.wcare.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductService {

    private ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo){
        this.repo = repo;
    }

    public List<Product> listAll() {
        return repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

}
