package com.example.investigation.controllers;

import com.example.investigation.models.AnswerOption;
import com.example.investigation.repositories.AnswerOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answerOption")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @GetMapping("/all")
    public Iterable<AnswerOption> getAllPatients() {return answerOptionRepository.findAll();}

    @GetMapping("/byItem/{item}")
    public Iterable<AnswerOption> getQuestionsByItem(@PathVariable String item) {
        return answerOptionRepository.findByItem(item);
    }

    @GetMapping("/byId/{id}")
    public Iterable<AnswerOption> getAnswerOptionById(@PathVariable long id) {
        return answerOptionRepository.findById(id);
    }

    @GetMapping("/byQuestionId/{question_id}")
    public Iterable<AnswerOption> getAnswerOptionByQuestionId(@PathVariable long question_id){
        return answerOptionRepository.findByQuestionId(question_id);
    }
}
