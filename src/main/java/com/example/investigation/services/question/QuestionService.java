package com.example.investigation.services.question;

import com.example.investigation.models.Patient;
import com.example.investigation.models.Question;

import java.util.Collection;

public interface QuestionService {

    Question findById(long id);
    Question update (long id, Question question);
    Collection<Question> findByText(String text);
    Collection<Question> findAll();
    Collection<Question> findBySueveyId(long survey_id);
    //Question add(long num, String text, long id);
    Question add(Question question);

    void deleteById(Long id);



}
