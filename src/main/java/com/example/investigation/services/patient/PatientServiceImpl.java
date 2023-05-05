package com.example.investigation.services.patient;

import com.example.investigation.exception.MiwaException;
import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.Patient;
import com.example.investigation.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{

    private final Logger logger =  LoggerFactory.getLogger(PatientServiceImpl.class);
    //private final Logger logger;
    private final PatientRepository patientRepository;

    //public  PatientServiceImpl(PatientRepository patientRepository){
//        this.patientRepository = patientRepository;
//    }
    @Override
    public Patient findById(long id) {
        return getPatient(id);
    }

    private Patient getPatient(long id) {
        return patientRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("This is not exist."));
    }


    @Override
    public Collection<Patient> findByName(String name) {
        return patientRepository.findAllByFullName(name);
    }

    @Override
    public Collection<Patient> findBySocialNumber(String socialNumber) {
        return patientRepository.findBySocialNumber(socialNumber);
    }

    @Override
    public Collection<Patient> findByEmail(String email) {
        return patientRepository.findByEmail(email);
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

//        return patientRepository.save(patient);
    }


    @Override
    public Patient update(long id, Patient patient){
        //Old patient
        //Patient existPatient = patientRepository.findById(id).get(); // do not use .get
        Patient existPatient = getPatient(id);

//        if(existPatient==null){
//            return null;
//        }
        if(patient.getFullName()!= null && !patient.getFullName().isEmpty()){ // check name is not Null and not empty
            existPatient.setFullName(patient.getFullName());
        }
        if(patient.getSocialNumber()!=null && !patient.getSocialNumber().isEmpty()){
            existPatient.setSocialNumber(patient.getSocialNumber());
        }
        if(patient.getEmail()!=null && !patient.getEmail().isEmpty()){
            existPatient.setEmail(patient.getEmail());
        }
        return patientRepository.save(existPatient);

    }

    @Override
    public void deleteById(Long id) {
        if (patientRepository.existsById(id)){
            Patient patient = patientRepository.findById(id).get();
            // I need to set null to the all answers
            // Otherwise I connot delete patient;
            patientRepository.delete(patient);
        } else logger.warn( "Patient id: "+ id + " doesn´t exist ");

    }
}
