package com.example.wcare.dao;

import com.example.wcare.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OponintementRepository extends JpaRepository<Appointments,Long> {
}
