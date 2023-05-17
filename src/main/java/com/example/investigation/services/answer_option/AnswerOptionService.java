package com.example.investigation.services.answer_option;

import com.example.investigation.models.AnswerOption;

import java.util.Collection;

public interface AnswerOptionService {

    AnswerOption findById(long id);
    AnswerOption update(long id, AnswerOption answerOption);

    AnswerOption updateQuestionById(long question_id, long ao_id);
    Collection<AnswerOption> findAllByItem(String item );
    Collection<AnswerOption> findAll();
    Collection<AnswerOption> findByQuestionId (long question_id );
    AnswerOption add (AnswerOption answerOption);
    void deleteById(Long id);


}
