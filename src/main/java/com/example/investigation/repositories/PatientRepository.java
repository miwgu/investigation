package com.example.investigation.repositories;

import com.example.investigation.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllById(long id);
    List<Patient> findByName(String name);
    Patient findById(long id);
}
