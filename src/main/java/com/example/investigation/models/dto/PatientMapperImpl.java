package com.example.investigation.models.dto;

import com.example.investigation.models.entity.Answer;
import com.example.investigation.models.entity.Patient;
import com.example.investigation.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientMapperImpl implements PatientMapper{
    private final AnswerRepository answerRepository;
    @Override
    public PatientDTO toPatientDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setSocialNumber(patient.getSocialNumber());
        dto.setFullName(patient.getFullName());
        dto.setEmail(patient.getEmail());
        if ( patient.getAnswers() != null ) {
            List<Long> answersId = patient.getAnswers().stream().map(Answer::getId).collect(Collectors.toList());
            dto.setAnswers(answersId);
        }
        return dto;
    }

    @Override
    public Patient toPatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setSocialNumber(dto.getSocialNumber());
        patient.setFullName(dto.getFullName());
        patient.setEmail(dto.getEmail());
        if (dto.getAnswers() != null) {
            List<Answer> answers = dto.getAnswers().stream()
                    .map(answerId -> answerRepository.findById(answerId).orElseThrow(() -> new RuntimeException("Answer with id " + answerId + " not found")))
                    .collect(Collectors.toList());
            patient.setAnswers(answers);
        }
        return patient;
    }
}
