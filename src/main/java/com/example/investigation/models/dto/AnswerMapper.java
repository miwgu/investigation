package com.example.investigation.models.dto;

import com.example.investigation.models.entity.Answer;

public interface AnswerMapper {
    AnswerDTO dto(Answer answer);
    Answer answer(AnswerDTO dto);
}
