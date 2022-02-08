package com.example.investigation.controllers;

import com.example.investigation.models.Question;
import com.example.investigation.models.Survey;
import com.example.investigation.repositories.QuestionRepository;
import com.example.investigation.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
     private QuestionRepository questionRepository;

    @Autowired
     private SurveyRepository surveyRepository;

    @GetMapping("/all")
    public Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


    @GetMapping("/byText/{text}")
    public Iterable<Question> getQuestionsByText(@PathVariable String text) {
        return questionRepository.findByText(text);
    }

    @GetMapping("/byId/{id}")
    public Iterable<Question> getQuestionsById(@PathVariable long id) {

        return questionRepository.findAllById(id);
    }

    /*
    * http://localhost:8080/question/bySurveyId/1
    * */
    @GetMapping("/bySurveyId/{survey_id}")
    public Iterable<Question> getQuestionsBySurveyId(@PathVariable long survey_id){
        return questionRepository.findBySurveyId(survey_id);
    }

    /*
    * http://localhost:8080/question/add?text=test2&id=2
    * */
    @GetMapping(path="/add")
    public  String addQuestion(@RequestParam String text, @RequestParam long id ){

        Survey existingSurvey = surveyRepository.findAllById(id);
        Question question =new Question(text, existingSurvey);
        questionRepository.save(question);

        return "Question "+text+" was added";

    }
}
