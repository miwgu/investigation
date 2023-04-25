package com.example.investigation.repositories;

import com.example.investigation.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    //List<Patient> findAllById(long id);
    Optional<Patient> findAllById(long id);
    List<Patient> findByFullName(String fullName);
    List<Patient> findAllBySocialNumber(String socialNumber);
    List<Patient> findAllByEmail(String email);

    Patient findById(long id);
}
