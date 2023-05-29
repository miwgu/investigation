package com.example.investigation.services.survey;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.Survey;
import com.example.investigation.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepository surveyRepository;

    @Override
    public Survey findById(long id) {
        return getSurvey(id);
    }

    private Survey getSurvey(long id){
        return surveyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This survey_id, "+ id+" does not exist"));
    }

    @Override
    public Collection<Survey> findByName(String name) {
        return surveyRepository.findAllByName(name);
    }

    @Override
    public Collection<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey add(Survey survey) {
        if(survey !=null)
        return surveyRepository.save(survey);
        return null;
    }

    @Override
    public Survey update(long id, Survey survey) {
        //Old survey
        Survey existSurvey = getSurvey(id);

        if(survey.getName() !=null && !survey.getName().isEmpty())
            existSurvey.setName(survey.getName());
        return surveyRepository.save(existSurvey);
    }

    @Override
    public void deleteById(Long id) {
            Survey survey = findById(id);
        System.out.println(survey);
            surveyRepository.delete(survey);
    }
}
