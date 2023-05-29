package com.example.investigation.models.dto;

import com.example.investigation.models.entity.Question;

public interface QuestionMapper {
    QuestionDTO toQuestionDTO(Question question);
    Question toQuestion(QuestionDTO dto);
}
