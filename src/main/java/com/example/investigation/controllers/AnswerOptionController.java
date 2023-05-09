package com.example.investigation.controllers;

import com.example.investigation.models.Answer;
import com.example.investigation.models.AnswerOption;
import com.example.investigation.models.Question;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.AnswerRepository;
import com.example.investigation.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answerOption")
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/all")
    public Iterable<AnswerOption> getAllPatients() {return answerOptionRepository.findAll();}

    @GetMapping("/byItem/{item}")
    public Iterable<AnswerOption> getQuestionsByItem(@PathVariable String item) {
        return answerOptionRepository.findByItem(item);
    }

    @GetMapping("/byId/{id}")
    public Iterable<AnswerOption> getAnswerOptionById(@PathVariable long id) {
        return answerOptionRepository.findAllById(id);
    }

    @GetMapping("/byQuestionId/{question_id}")
    public Iterable<AnswerOption> getAnswerOptionByQuestionId(@PathVariable long question_id){
        return answerOptionRepository.findByQuestionId(question_id);
    }

    /*
     * http://localhost:8080/answerOption/add?item=test1&id=1
     * */
    /*
    @PostMapping(path="/add")
    public  String addAnswerOption(@RequestParam String item, @RequestParam long id ){

        Question existingQuestion = questionRepository.findById(id);
        AnswerOption answerOption =new AnswerOption(item, existingQuestion);
        answerOptionRepository.save(answerOption);

        return "Answer Option:"+item+" was added";
    }

     */

    /*
     *http://localhost:8080/answerOption/delete/37
     * */
    @DeleteMapping(path = "/delete/{answer_op_id}")
    public  String deleteAnswerOption(@PathVariable("answer_op_id") long id){
        List<Answer> AnswerUpdateAOp = answerRepository.findByAnswerOptionId(id);
        AnswerUpdateAOp.forEach(a -> a.setAnswerOption(null));
        AnswerOption existingAO = answerOptionRepository.findById(id);
        answerOptionRepository.delete(existingAO);

        return "Answer Option ID:"+id+ " "+"Answer Option:"+existingAO.getItem()+ " was deleted";
    }

}
