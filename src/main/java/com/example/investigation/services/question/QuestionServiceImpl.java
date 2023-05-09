package com.example.investigation.services.question;

import com.example.investigation.exception.ResourceNotFoundException;
import com.example.investigation.models.Question;
import com.example.investigation.models.Survey;
import com.example.investigation.repositories.QuestionRepository;
import com.example.investigation.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    @Override
    public Question findById(long id) {
        return getQuestion(id);
    }

    private Question getQuestion(long id) {
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This id, " + id + " does not exist."));
    }


    @Override
    public Collection<Question> findByText(String text) {
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
        if(question != null)
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

        if(question.getNum()!=0) {
            existingQuestion.setNum(question.getNum());
        }
        if(question.getText()!=null && !question.getText().isEmpty()) {
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
        if(existingQuestion.getSurvey().getId()!=0) {
            existingQuestion.setSurvey(surveyRepository.getById(survey_id));
            questionRepository.save(existingQuestion);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {

        Question question = findById(id);
        questionRepository.delete(question);
    }
}
