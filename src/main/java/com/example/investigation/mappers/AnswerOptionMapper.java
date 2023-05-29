package com.example.investigation.mappers;

import com.example.investigation.models.entity.AnswerOption;
import com.example.investigation.models.entity.Question;
import com.example.investigation.models.dto.AnswerOptionDTO;
import com.example.investigation.services.answer.AnswerService;
import com.example.investigation.services.question.QuestionService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class AnswerOptionMapper {

    @Autowired
    protected QuestionService questionService;

    protected AnswerService answerService;

    @Mapping(target = "question", source = "question.id")
    public  abstract AnswerOptionDTO answerOptionToAnswerOptionDto (AnswerOption answerOption);

    @Mapping(target = "question", source = "question.id")
    public  abstract Collection <AnswerOptionDTO> answerOptionToAnswerOptionDto (Collection<AnswerOption> answerOptions);

    @Mapping(target = "question", source = "question", qualifiedByName = "answerOptionIdToAnswerOption")
    public  abstract AnswerOption answerOptionDtoToAnswerOption(AnswerOptionDTO answerOptionDTO);

    @Named("answerOptionIdToAnswerOption")
    Question mapIdToQuestion (long id) {
        return questionService.findById(id);
    }

}

