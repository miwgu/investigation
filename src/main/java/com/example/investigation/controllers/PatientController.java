package com.example.investigation.controllers;

import com.example.investigation.models.Patient;
import com.example.investigation.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/all")
    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/byName/{name}")
    public Iterable<Patient> getPatientByName(@PathVariable String name){
        return patientRepository.findByName(name);
    }

    @GetMapping("/byId/{id}")
    public Iterable<Patient> getPatientsById(@PathVariable long id) {
        return patientRepository.findAllById(id);

    }


}

