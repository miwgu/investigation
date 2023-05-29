package com.example.investigation.models.dto;

import com.example.investigation.models.entity.AnswerOption;
import com.example.investigation.models.entity.Question;
import com.example.investigation.models.entity.Survey;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper{
    private final AnswerOptionRepository answerOptionRepository;
    private final SurveyRepository surveyRepository;
    @Override
    public QuestionDTO toQuestionDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setNum(question.getNum());
        dto.setText(question.getText());
        if (question.getSurvey() != null) {
            dto.setSurvey(question.getSurvey().getId());
        }
        if (question.getAnswerOptions() != null) {
            dto.setAnswerOptions(question.getAnswerOptions().stream().map(AnswerOption::getId).collect(java.util.stream.Collectors.toList()));
        }
        return dto;
    }

    @Override
    public Question toQuestion(QuestionDTO dto) {
        Question question = new Question();
        question.setId(dto.getId());
        question.setNum(dto.getNum());
        question.setText(dto.getText());
        if (dto.getSurvey() != 0) {
            Survey survey = surveyRepository.findById(dto.getSurvey()).orElseThrow(() -> new RuntimeException("Survey with id " + dto.getSurvey() + " not found"));
            question.setSurvey(survey);
        }
        if (dto.getAnswerOptions() != null) {
            List<AnswerOption> answerOptionIds = dto.getAnswerOptions().stream().map(answerOptionId -> answerOptionRepository.findById(answerOptionId).orElseThrow(() -> new RuntimeException("AnswerOption with id " + answerOptionId + " not found"))).collect(Collectors.toList());
            question.setAnswerOptions(answerOptionIds);
        }

        return question;
    }
}
