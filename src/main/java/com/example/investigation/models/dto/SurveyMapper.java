package com.example.investigation.models.dto;

import com.example.investigation.models.entity.Survey;

public interface SurveyMapper {

        SurveyDTO toSurveyDto(Survey survey);
        Survey toSurvey(SurveyDTO surveyDto);
}
