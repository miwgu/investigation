package com.example.investigation.services.question;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.Answer;
import com.example.investigation.models.AnswerOption;
import com.example.investigation.models.Question;
import com.example.investigation.models.Survey;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.AnswerRepository;
import com.example.investigation.repositories.QuestionRepository;
import com.example.investigation.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerRepository answerRepository;


    @Override
    public Question findById(long id) {
        return getQuestion(id);
    }

    private Question getQuestion(long id) {
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This id, " + id + " does not exist."));
    }


    @Override
    public Collection<Question> findAllByText(String text) {
        return questionRepository.findAllByText(text);
    }

    @Override
    public Collection<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Collection<Question> findBySurveyId(long survey_id) {
        return questionRepository.findBySurveyId(survey_id);
    }

    @Override
    public Question add(Question question) {
        if (question != null)
            return questionRepository.save(question);
        return null;
    }

   /* @Override
    public Question add(long num, String text, long survey_id) {
        if(num !=0 && text !=null && survey_id != 0) {
            Survey existingSurvey = surveyRepository.getById(survey_id);
            Question question = new Question(num, text, existingSurvey);

        return questionRepository.save(question);
        }
        return null;
    }

    */


    @Override
    public Question update(long id, Question question) {

        Question existingQuestion = getQuestion(id);

        if (question.getNum() != 0) {
            existingQuestion.setNum(question.getNum());
        }
        if (question.getText() != null && !question.getText().isEmpty()) {
            existingQuestion.setText(question.getText());
        }
//        if(question.getSurvey().getId()!=0) {
//            existingQuestion.setSurvey(question.getSurvey().getId());
//        }
        return questionRepository.save(existingQuestion);
    }

    @Override
    public Void updateSurveyById(long survey_id, long question_id) {
        Question existingQuestion = getQuestion(question_id);
        if (existingQuestion.getSurvey().getId() != 0) {
            existingQuestion.setSurvey(surveyRepository.getById(survey_id));
            questionRepository.save(existingQuestion);
        }
        return null;
    }

    /**
     * This method set null to answer_op_id in answer Table.
     * But it is not good to delete answer because some patients answered to the question already
     */
/*
    @Override
    public void deleteById(Long id) {
        List<AnswerOption> answerOptions = answerOptionRepository.findByQuestionId(id);
        if (!answerOptions.isEmpty()) {
            for (AnswerOption answerOption : answerOptions) {
                List<Answer> answers = answerRepository.findByAnswerOptionId(answerOption.getId());
                for (Answer answer : answers) {
                    answer.setAnswerOption(null);

                }
                answerOption.setQuestion(null);

            }
            Question question = findById(id);
            questionRepository.delete(question);
        }
    }
    */

    //TODO:  you need to think about survey model. It should be have active variable. If survey status, active is true you cannot delete question.

    /**
     * This method deletes question if this question is not answered by patient through answer_option.
     */
    @Override
    public void deleteById(Long id) {
        Question findQustionById = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("THIS_QUESTION_ID, " + id + " DOES_NOT_EXIST."));
        if (findQustionById != null) {

            List<AnswerOption> answerOptions = answerOptionRepository.findByQuestionId(id);
            if (!answerOptions.isEmpty()) {
                boolean canDelete = true;

                for (AnswerOption answerOption : answerOptions) {
                    List<Answer> answers = answerRepository.findByAnswerOptionId(answerOption.getId());
                    if (!answers.isEmpty()) {
                        canDelete = false;
                        System.out.println("You can not delete this question because user already answered");
                        break;
                    }
                }

                if (canDelete) {
                    for (AnswerOption answerOption : answerOptions) {
                        answerOption.setQuestion(null);
                    }
                    Question question = findById(id);
                    questionRepository.delete(question);
                    System.out.println("Question id," + id + " is deleted");
                }

            } else {
                Question question = findById(id);
                questionRepository.delete(question);
                System.out.println("Question id," + id + " is deleted");

            }
        }
    }

}

