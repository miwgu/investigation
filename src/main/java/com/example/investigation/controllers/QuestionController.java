package com.example.investigation.controllers;

import com.example.investigation.models.AnswerOption;
import com.example.investigation.models.Question;
import com.example.investigation.models.Survey;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.QuestionRepository;
import com.example.investigation.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
     private QuestionRepository questionRepository;

    @Autowired
     private AnswerOptionRepository answerOptionRepository;

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
    * http://localhost:8080/api/v1/question/bySurveyId/1
    * */
    @GetMapping("/bySurveyId/{survey_id}")
    public Iterable<Question> getQuestionsBySurveyId(@PathVariable long survey_id){
        return questionRepository.findBySurveyId(survey_id);
    }

    /*
    * http://localhost:8080/api/v1/question/add?num=1&text=test1 psoriasis&id=1
    * */
    @PostMapping(path="/add")
    public  String addQuestion(@RequestParam long num, @RequestParam String text, @RequestParam long id ){

        Survey existingSurvey = surveyRepository.findAllById(id);
        Question question =new Question(num, text, existingSurvey);
        questionRepository.save(question);

        return "Question No:"+num+ " " +text+" was added";

    }

    /*
    *http://localhost:8080/api/v1/question/delete/18
    * */
    @DeleteMapping(path = "/delete/{question_id}")
    public  String deleteQuestion(@PathVariable("question_id") long id){
        List<AnswerOption> AnOpUpdateQuestion = answerOptionRepository.findByQuestionId(id);
        AnOpUpdateQuestion.forEach(aop -> aop.setQuestion(null));
        Question existingQuestion = questionRepository.findById(id);
        questionRepository.delete(existingQuestion);

        return "Question ID:"+id+ " "+"Question:"+existingQuestion.getText()+ " was deleted";
    }

}
