package com.example.investigation.services.answer;

import com.example.investigation.models.Answer;

import java.util.Collection;

public interface AnswerService {

    Answer findById(long id);

    //Answer update(long id, Answer answer); // Do not need id

    Collection <Answer> findAllByPatientId(long patient_id);
    Collection <Answer> findAllByAnswerOptionId(long ao_id);
    Collection<Answer> findAll();
    Answer add(Answer answer);



}
