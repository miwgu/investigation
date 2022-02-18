package com.example.investigation.controllers;

import com.example.investigation.models.Answer;
import com.example.investigation.models.Patient;
import com.example.investigation.repositories.AnswerRepository;
import com.example.investigation.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AnswerRepository answerRepository;

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

    /*
     * http://localhost:8080/patient/add?name=test1
     * */
    @GetMapping(path="/add")
    public  String addPatient(@RequestParam String name){

        Patient patient = new Patient(name);
        patientRepository.save(patient);

        return "Patient "+name+" was added";

    }

    /*
     *http://localhost:8080/patient/delete/3
     * */
    @DeleteMapping(path = "/delete/{id}")
    public String deletePatient(@PathVariable long id){
        List<Answer> AnswerUpdatePatient = answerRepository.findByPatientId(id);
        AnswerUpdatePatient.forEach(p -> p.setPatient(null));
        Patient existingPatient = patientRepository.findById(id);
        patientRepository.delete(existingPatient);

        return "Patient ID:"+ id +" "+"Patient:"+ existingPatient.getName()+" was deleted";

    }
    /*
     *http://localhost:8080/patient/update
     * */
    @PutMapping(value="/update",consumes="application/json",produces="application/json")
    public Patient updatePatient (@RequestBody Patient patient){
        Patient existingPatient= patientRepository.findById(patient.getId());
        patient.setId(existingPatient.getId());// set the same id and name is changed to the new one

        return patientRepository.save(patient);
    }


}

