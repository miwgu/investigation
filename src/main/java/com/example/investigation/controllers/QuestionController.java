package com.example.investigation.controllers;

import com.example.investigation.mappers.QuestionMapper;
import com.example.investigation.models.Question;
import com.example.investigation.models.dto.QuestionDTO;
import com.example.investigation.services.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    private final QuestionMapper questionMapper;
    private final QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity getAll(){
        Collection<QuestionDTO> questions = questionMapper.questionToQuestionDto(
                questionService.findAll());
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/search/byId/{id}")
    public ResponseEntity <QuestionDTO> getById(@PathVariable long id) {
        QuestionDTO questionDTO = questionMapper.questionToQuestionDto(questionService.findById(id));
        return ResponseEntity.ok(questionDTO);
    }


    @GetMapping("/search/byText/{text}")
    public ResponseEntity <Collection<QuestionDTO>> getByText(@PathVariable String text) {
        Collection<QuestionDTO> questionDTOS = questionMapper.questionToQuestionDto(questionService.findAllByText(text));
        return ResponseEntity.ok(questionDTOS);
    }

    /*
    * http://localhost:8080/api/v1/question/search/bySurveyId/1
    * */
    @GetMapping("/search/bySurveyId/{survey_id}")
    public ResponseEntity <Collection<QuestionDTO>> getBySurveyId(@PathVariable long survey_id){
        Collection<QuestionDTO> questionDTOS = questionMapper.questionToQuestionDto(questionService.findBySurveyId(survey_id));
        return ResponseEntity.ok(questionDTOS);
    }

    /*
    * http://localhost:8080/api/v1/question/add
    * */

    @PostMapping(path="/add")
    public QuestionDTO addQuestion (@RequestBody QuestionDTO questionDTO ){
        Question question = questionService.add(questionMapper.questionDtoToQuestion(questionDTO));
        return questionMapper.questionToQuestionDto(question);
    }

    /*
     * http://localhost:8080/api/v1/question/update/1
     * */
    @PatchMapping("/update/{id}")
    public QuestionDTO updateQuestion (@RequestBody QuestionDTO questionDTO, @PathVariable long id){
        Question question = questionMapper.questionDtoToQuestion(questionDTO);
        return questionMapper.questionToQuestionDto(questionService.update(id,question));
    }

    @PutMapping("/update/questionId/{question_id}/surveyId/{survey_id}")
    public ResponseEntity updateSurveyById (@PathVariable int survey_id, @PathVariable long question_id){
        questionService.updateSurveyById(survey_id,question_id);
        return ResponseEntity.ok().build();
    }


   //TODO: Need to change update method(updateQuestion) to change survey id directly
/*
    @PatchMapping("/update/questionId/{question_id}/surveyId/{survey_id}")
    public QuestionDTO updateSurveyById_2 (@PathVariable int survey_id, @PathVariable long question_id){
        Question question = questionService.updateSurveyById(survey_id, question_id);
        return questionMapper.questionToQuestionDto(question);
    }
*/


    /*
    *http://localhost:8080/api/v1/question/delete/18
    * */

    @DeleteMapping( "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)//200
    public  String deleteQuestion (@PathVariable long id){
        return questionService.deleteById(id);
       // return "DELETE_SUCCESSFULLY";
    }


}
