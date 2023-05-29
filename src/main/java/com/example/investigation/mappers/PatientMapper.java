package com.example.investigation.mappers;

import com.example.investigation.models.entity.Patient;
import com.example.investigation.models.dto.PatientDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PatientMapper {
  PatientDTO patientToPatientDto (Patient patient);

  Patient patientDtoToPatient (PatientDTO patientDTO);

  Collection<PatientDTO> patientToPatientDto(Collection<Patient> patients);
}
