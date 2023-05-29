package com.example.investigation.services.patient;

import com.example.investigation.models.entity.Patient;


import java.util.Collection;

public interface PatientService{

    Patient findById(long id);
    Patient update(long id, Patient patient);
    Collection<Patient> findByName(String name);
    Collection<Patient> findBySocialNumber(String socialNumber);
    Collection<Patient> findByEmail(String email);

    Collection<Patient> findAll();

    Patient add(Patient patient);

    String deleteById(Long id);

}
