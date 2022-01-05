package com.example.investigation.controllers;

import com.example.investigation.models.Survey;
import com.example.investigation.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;


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
}
