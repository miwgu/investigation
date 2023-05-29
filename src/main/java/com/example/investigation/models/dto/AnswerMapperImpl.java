package com.example.investigation.models.dto;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.entity.Answer;
import com.example.investigation.models.entity.AnswerOption;
import com.example.investigation.models.entity.Patient;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerMapperImpl implements AnswerMapper{
    private final PatientRepository patientRepository;
    private final AnswerOptionRepository answerOptionRepository;

    @Override
    public AnswerDTO toAnswerDto(Answer answer) {
        AnswerDTO dto = new AnswerDTO();
        dto.setId(answer.getId());
        if ( answer.getPatient() != null ) {
            dto.setPatient( answer.getPatient().getId() );
        }
        if ( answer.getAnswerOption() != null ) {
            dto.setAnswerOption( answer.getAnswerOption().getId() );
        }
        return dto;
    }

    @Override
    public Answer toAnswer(AnswerDTO dto) {
        Answer answer = new Answer();
        answer.setId( dto.getId() );
        // if the answer has a patient, we need to fetch it from the database
        // and set it to the answer object
        if ( dto.getPatient() != 0 ) {
            Patient patient = patientRepository.findById(dto.getPatient()).orElseThrow(() -> new ResourceNotFoundException("Patient with id " + dto.getPatient() + " not found"));
            answer.setPatient(patient);
        }

        // if the answer has an answerOption, we need to fetch it from the database
        // and set it to the answer object
        if ( dto.getAnswerOption() != 0 ) {
            AnswerOption answerOption = answerOptionRepository.findById(dto.getAnswerOption()).orElseThrow(() -> new ResourceNotFoundException("AnswerOption with id " + dto.getAnswerOption() + " not found"));
            answer.setAnswerOption(answerOption);
        }

        return answer;
    }
}
