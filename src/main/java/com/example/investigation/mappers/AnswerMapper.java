package com.example.investigation.mappers;

import com.example.investigation.models.Answer;
import com.example.investigation.models.dto.AnswerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public abstract class AnswerMapper {

    @Mapping(target="patient", source="patient.id")
    @Mapping(target="answerOption", source="answerOption.id")
    public abstract AnswerDTO answerToAnswerDto(Answer answer);
}
