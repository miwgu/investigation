package com.example.investigation.services.answer;

import com.example.investigation.models.entity.Answer;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface AnswerService {

    Answer findById(long id);

    //Answer update(long id, Answer answer); // Do not need id

    Collection <Answer> findAllByPatientId(long patient_id);
    Collection <Answer> findAllByAnswerOptionId(long ao_id);

    List<Map<String, Object>> findBySurveyId(long survey_id);

    //    Collection <Answer> findBySurveyId(long survey_id);
    Collection<Answer> findAll();

    Answer add(Long patient_id, Long ao_id);

    //Answer add(Answer answer,Long patient_id, Long ao_id);

    Answer create(Answer toAnswer);
    //Answer add(Answer answer);




    //List<Map<String, Object>> getBySurveyId(long surveyId);
}
