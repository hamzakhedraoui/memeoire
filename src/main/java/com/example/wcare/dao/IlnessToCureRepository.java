package com.example.wcare.dao;

import com.example.wcare.model.IlnessToCure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IlnessToCureRepository extends JpaRepository<IlnessToCure, Long> {
}
