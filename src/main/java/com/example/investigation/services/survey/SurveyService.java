package com.example.investigation.services.survey;

import com.example.investigation.models.Survey;

import java.util.Collection;

public interface SurveyService {

    Survey findById(long id);

    Survey update(long id, Survey survey);

    Collection<Survey> findByName(String name);

    Collection<Survey> findAll();

    Survey add(Survey survey);

    void deleteById(Long id);

}
