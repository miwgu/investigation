package com.example.investigation.services.patient;

import com.example.investigation.models.Patient;
import com.example.investigation.repositories.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PatientServiceImpl implements PatientService{
    private final Logger logger =  LoggerFactory.getLogger(PatientServiceImpl.class);
    private final PatientRepository patientRepository;

    public  PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).get();
    }


    @Override
    public Collection<Patient> findByName(String name) {
        return patientRepository.findByFullName(name);
    }

    @Override
    public Collection<Patient> findBySocialNumber(String socialNumber) {
        return patientRepository.findAllBySocialNumber(socialNumber);
    }

    @Override
    public Collection<Patient> findByEmail(String email) {
        return patientRepository.findAllByEmail(email);
    }

    @Override
    public Collection<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient add(Patient patient) {
        if(patient != null)
            return patientRepository.save(patient);
        return null;
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deleteById(Long id) {
        if (patientRepository.existsById(id)){
            Patient patient = patientRepository.findById(id).get();
            // I need to set null to the all answers to set null
            // Otherwise I connot delete patient;
            patientRepository.delete(patient);
        } else logger.warn( "Patient id: "+ id + " doesn´t exist ");

    }
}
