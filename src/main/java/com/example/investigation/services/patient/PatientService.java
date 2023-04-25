package com.example.investigation.services.patient;

import com.example.investigation.models.Patient;
import com.example.investigation.services.CrudService;

import java.util.Collection;
import java.util.Collections;

public interface PatientService extends CrudService<Patient, Long> {
    Collection<Patient> findByName(String name);
    Collection<Patient> findBySocialNumber(String socialNumber);
    Collection<Patient> findByEmail(String email);



}
