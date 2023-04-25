package com.example.investigation.controllers;

import com.example.investigation.models.Question;
import com.example.investigation.models.Survey;
import com.example.investigation.repositories.QuestionRepository;
import com.example.investigation.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
@CrossOrigin(origins = "http://localhost:3000")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping("/all")
    public Iterable<Survey> getAllSurvey(){
        return surveyRepository.findAll();
    }

    @GetMapping("/byName/{name}")
    public Survey getSurveyByName(@PathVariable String name){

        return surveyRepository.findByName(name);
    }

    @GetMapping("/byid/{id}")
    public  Survey getSurveyById(@PathVariable long id){

        return surveyRepository.findAllById(id);
    }

    /*
    * http://localhost:8080/survey/add?name=test
    * */
    @GetMapping(path="/add")
    public  String addSurvey(@RequestParam String name){

        Survey survey = new Survey(name);
        surveyRepository.save(survey);

        return "Survey "+name+" was added";

    }

    /*
     *http://localhost:8080/survey/delete/3
     * */
    @DeleteMapping(path = "/delete/{id}")
    public String deleteSurvey(@PathVariable long id){
        List<Question> QuestionUpdateSurvey = questionRepository.findBySurveyId(id);
        QuestionUpdateSurvey.forEach(q -> q.setSurvey(null));
        Survey existingSurvey = surveyRepository.findAllById(id);
        surveyRepository.delete(existingSurvey);

        return "Survey ID:"+ id +" "+"Survey:"+ existingSurvey.getName()+" was deleted";

    }
    /*
    *http://localhost:8080/survey/update
    * */
    @PutMapping(value="/update",consumes="application/json",produces="application/json")
    public Survey updateSurvey (@RequestBody Survey survey){
        Survey existingSurvey= surveyRepository.findAllById(survey.getId());
        survey.setId(existingSurvey.getId());// set the same id and name is changed to the new one

        return surveyRepository.save(survey);
    }
}
