package com.example.investigation.services.patient;

import com.example.investigation.models.Patient;


import java.util.Collection;
import java.util.Collections;

public interface PatientService{

    Patient findById(long id);
    Patient update(long id, Patient patient);
    Collection<Patient> findByName(String name);
    Collection<Patient> findBySocialNumber(String socialNumber);
    Collection<Patient> findByEmail(String email);

    Collection<Patient> findAll();

    Patient add(Patient patient);

    void deleteById(Long id);

}
