package com.example.investigation.services.question;

import com.example.investigation.models.Patient;
import com.example.investigation.models.Question;

import java.util.Collection;

public interface QuestionService {

    Question findById(long id);
    Question update (long id, Question question);

    //Void updateSurveyById (long survey_id, long question_id);
    Question updateSurveyById (long survey_id, long question_id);
    Collection<Question> findAllByText(String text);
    Collection<Question> findAll();
    Collection<Question> findBySurveyId(long survey_id);
    //Question add(long num, String text, long id);
    Question add(Question question);

    String deleteById(Long id);



}
