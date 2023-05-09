package com.example.investigation.controllers;

import com.example.investigation.mappers.QuestionMapper;
import com.example.investigation.models.Question;
import com.example.investigation.models.dto.QuestionDTO;
import com.example.investigation.services.question.QuestionService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/byId/{id}")
    public ResponseEntity <QuestionDTO> getById(@PathVariable long id) {
        QuestionDTO questionDTO = questionMapper.questionToQuestionDto(questionService.findById(id));
        return ResponseEntity.ok(questionDTO);
    }


    @GetMapping("/byText/{text}")
    public ResponseEntity <Collection<QuestionDTO>> getByText(@PathVariable String text) {
        Collection<QuestionDTO> questionDTOS = questionMapper.questionToQuestionDto(questionService.findByText(text));
        return ResponseEntity.ok(questionDTOS);
    }

    /*
    * http://localhost:8080/api/v1/question/bySurveyId/1
    * */
    @GetMapping("/bySurveyId/{survey_id}")
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

    @PutMapping("/update/{survey_id}/{question_id}/survey")
    public ResponseEntity updadteSurveyById (@PathVariable int survey_id, @PathVariable long question_id){
        questionService.updateSurveyById(survey_id,question_id);
        return ResponseEntity.ok().build();
    }


    /*
    *http://localhost:8080/api/v1/question/delete/18
    * */
    /*
    @DeleteMapping(path = "/delete/{question_id}")
    public  String deleteQuestion(@PathVariable("question_id") long id){
        List<AnswerOption> AnOpUpdateQuestion = answerOptionRepository.findByQuestionId(id);
        AnOpUpdateQuestion.forEach(aop -> aop.setQuestion(null));
        Question existingQuestion = questionRepository.findById(id);
        questionRepository.delete(existingQuestion);

        return "Question ID:"+id+ " "+"Question:"+existingQuestion.getText()+ " was deleted";
    }

     */

}
