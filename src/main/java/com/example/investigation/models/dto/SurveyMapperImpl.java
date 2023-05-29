package com.example.investigation.models.dto;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.entity.Question;
import com.example.investigation.models.entity.Survey;
import com.example.investigation.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SurveyMapperImpl implements SurveyMapper {

    private final QuestionRepository questionRepository;

    @Override
    public SurveyDTO toSurveyDto(Survey survey) {
        SurveyDTO dto = new SurveyDTO();
        dto.setId(survey.getId());
        dto.setName(survey.getName());
        // if the survey has questions, we need to fetch them from the database
        // and set them to the survey object
        if (survey.getQuestions() != null) {
            List<Long> questionIDs = survey.getQuestions().stream().map(Question::getId).collect(Collectors.toList());
            dto.setQuestions(questionIDs);
        }
        return dto;
    }

    @Override
    public Survey toSurvey(SurveyDTO dto) {
        Survey survey = new Survey();
        survey.setId(dto.getId());
        survey.setName(dto.getName());
        // if the survey has questions, we need to fetch them from the database
        // and set them to the survey object
        if (dto.getQuestions() != null) {
            List<Question> questions = dto.getQuestions().stream()
                    .map(questionId -> questionRepository.findById(questionId)
                            .orElseThrow(() -> new ResourceNotFoundException("Question with id " + questionId + " not found")))
                    .collect(Collectors.toList());
            survey.setQuestions(questions);
        }
        return survey;
    }
}
