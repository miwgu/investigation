package com.example.investigation.models.dto;

import com.example.investigation.models.entity.Patient;

public interface PatientMapper {
    PatientDTO toPatientDTO(Patient patient);
    Patient toPatient(PatientDTO dto);
}
