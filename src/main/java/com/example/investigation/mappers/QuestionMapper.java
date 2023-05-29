package com.example.investigation.mappers;

import com.example.investigation.models.entity.Question;
import com.example.investigation.models.entity.Survey;
import com.example.investigation.models.dto.QuestionDTO;
import com.example.investigation.services.survey.SurveyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Mapper(componentModel ="spring")
public abstract class QuestionMapper {

    @Autowired
    protected SurveyService surveyService;

    /*
    * in the questionToQuestionDto method, target = "survey" means that
    * the survey property in the QuestionDTO class will be set as the target,
    * while source = "survey.id" means that the id property of the survey property in the Question entity will be used
    * as the value for the survey property in the QuestionDTO.
    * */
    @Mapping(target = "survey", source ="survey.id") // target -> survey in QuestionDTO, source -> survey.id in Question
    public abstract QuestionDTO questionToQuestionDto(Question question);
    @Mapping(target = "survey", source ="survey.id")
    public abstract Collection<QuestionDTO> questionToQuestionDto (Collection<Question> questions);
    @Mapping(target = "survey", source = "survey", qualifiedByName = "questionIdToQuestion") // target -> survey in Question, source -> survey in QuestionDTO
    public abstract Question questionDtoToQuestion(QuestionDTO questionDTO);

    /*
    * This method is used to map the id property of the survey property in the QuestionDTO
    *  to the corresponding Survey entity using the SurveyService component.
    * */

    @Named("questionIdToQuestion")
    Survey mapIdToQuestion (long id) {
        return surveyService.findById(id);
    }

}
