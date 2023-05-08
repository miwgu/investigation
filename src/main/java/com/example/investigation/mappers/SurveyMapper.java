package com.example.investigation.mappers;


import com.example.investigation.models.Survey;
import com.example.investigation.models.dto.SurveyDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
  SurveyDTO surveyToSurveyDto (Survey survey);

  Survey surveyDtoToSurvey(SurveyDTO surveyDTO);

  Collection<SurveyDTO> surveyToSurveyDto(Collection<Survey> surveys);
}
