package com.example.investigation.controllers;

import com.example.investigation.mappers.SurveyMapper;
import com.example.investigation.models.entity.Survey;
import com.example.investigation.models.dto.SurveyDTO;
import com.example.investigation.services.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
@CrossOrigin(origins = "http://localhost:3000")
public class SurveyController {
    private final SurveyMapper surveyMapper;
    private final SurveyService surveyService;


    @GetMapping("/all")
    public ResponseEntity getAll(){
        Collection<SurveyDTO> surveys = surveyMapper.surveyToSurveyDto(
                surveyService.findAll());
        return ResponseEntity.ok(surveys);
    }


    @GetMapping("/byId/{id}")
    public ResponseEntity <SurveyDTO> getById(@PathVariable long id){
        SurveyDTO surveyDTO = surveyMapper.surveyToSurveyDto(surveyService.findById(id));
        return ResponseEntity.ok(surveyDTO);
    }

    /*
     * http://localhost:8080/api/v1/survey/byName/cancer
     * */
    @GetMapping("/byName/{name}")
    public ResponseEntity <Collection<SurveyDTO>>getByName(@PathVariable String name){
        Collection<SurveyDTO> surveyDTOS = surveyMapper.surveyToSurveyDto(surveyService.findByName(name));
        return ResponseEntity.ok(surveyDTOS);
    }

    /*
     * http://localhost:8080/api/v1/survey/add
     * */
    @PostMapping(path="/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public  SurveyDTO addSurvey(@RequestBody SurveyDTO surveyDTO){
        Survey survey = surveyService.add(surveyMapper.surveyDtoToSurvey(surveyDTO));
        return surveyMapper.surveyToSurveyDto(survey);
    }



    /*
    *http://localhost:8080/api/v1/survey/update/4
    * */

    @PatchMapping("/update/{id}")
    public SurveyDTO updateSurvey (@RequestBody SurveyDTO surveyDTO, @PathVariable long id){
        Survey survey= surveyMapper.surveyDtoToSurvey(surveyDTO);
        return surveyMapper.surveyToSurveyDto(surveyService.update(id, survey));
    }


    /*
     *http://localhost:8080/api/v1/survey/delete/3
     * */

    @DeleteMapping( "/delete/{id}")
    public ResponseEntity deleteSurvey(@PathVariable long id){
        surveyService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
