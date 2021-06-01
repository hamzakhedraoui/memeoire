package com.example.wcare.dao;

import com.example.wcare.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecodRepository extends JpaRepository<MedicalRecord,Long> {
}
