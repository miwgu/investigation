package com.example.investigation.models.dto;

import com.example.investigation.models.entity.AnswerOption;

public interface AnswerOptionMapper {
    AnswerOptionDTO toAnswerOptionDTO(AnswerOption answerOption);
    AnswerOption toAnswerOption(AnswerOptionDTO dto);
}
