package com.example.investigation.models.dto;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.entity.Answer;
import com.example.investigation.models.entity.Patient;
import com.example.investigation.models.entity.Survey;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.PatientRepository;
import com.example.investigation.repositories.QuestionRepository;
import com.example.investigation.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerMapperImpl implements AnswerMapper {

    private final AnswerOptionRepository answerOptionRepository;
    private final PatientRepository patientRepository;

    @Override
    public AnswerDTO toAnswerDto(Answer answer) {

        AnswerDTO dto = new AnswerDTO();

        dto.setId(answer.getId());
        if (answer.getPatient() != null) {
            dto.setPatient(answer.getPatient().getId());
        }

        if (answer.getAnswerOption() != null) {
            dto.setPatient(answer.getAnswerOption().getId());
        }

        return dto;
    }


    @Override
    public Answer toAnswer(AnswerDTO dto) {
        Answer answer = new Answer();

        answer.setId(dto.getId());
        if (dto.getPatient() != 0) { // This is long id
            Patient patient = patientRepository.findById(dto.getPatient()).orElseThrow(() -> new ResourceNotFoundException("THIS_PATIENT_ID_DOES_NOT_EXIST."));
            answer.setPatient(patient);
        }
        if (dto.getAnswerOption() != 0) {
            answerOptionRepository.findById(dto.getAnswerOption()).orElseThrow(() -> new ResourceNotFoundException("THIS_ANSWER_OPTION_ID_DOES_NOT_EXIST."));
        }

        return answer;
    }
}
