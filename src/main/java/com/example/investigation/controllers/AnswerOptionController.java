package com.example.investigation.controllers;

import com.example.investigation.mappers.AnswerOptionMapper;
import com.example.investigation.models.Answer;
import com.example.investigation.models.AnswerOption;
import com.example.investigation.models.Question;
import com.example.investigation.models.dto.AnswerOptionDTO;
import com.example.investigation.models.dto.PatientDTO;
import com.example.investigation.repositories.AnswerOptionRepository;
import com.example.investigation.repositories.AnswerRepository;
import com.example.investigation.repositories.QuestionRepository;
import com.example.investigation.services.answer_option.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answerOption")
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerOptionController {

    private final AnswerOptionMapper answerOptionMapper;
    private final AnswerOptionService answerOptionService;

    private final AnswerOptionRepository answerOptionRepository;

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        Collection<AnswerOptionDTO> answerOptions = answerOptionMapper.answerOptionToAnswerOptionDto(
        answerOptionService.findAll());
        return ResponseEntity.ok(answerOptions);
    }


    @GetMapping("search/byId/{id}")
    public ResponseEntity<AnswerOptionDTO> getById(@PathVariable long id) {
        AnswerOptionDTO answerOptionDTO = answerOptionMapper.answerOptionToAnswerOptionDto(answerOptionService.findById(id));
        return ResponseEntity.ok(answerOptionDTO);
    }

    @GetMapping("search/byItem/{item}")
    public ResponseEntity <Collection<AnswerOptionDTO>> getByItem(@PathVariable String item) {
        Collection <AnswerOptionDTO> answerOptionDTOS = answerOptionMapper.answerOptionToAnswerOptionDto(answerOptionService.findAllByItem(item));
        return ResponseEntity.ok(answerOptionDTOS) ;
    }


    @GetMapping("search/byQuestionId/{question_id}")
    public ResponseEntity<Collection<AnswerOptionDTO>>getByQuestionId(@PathVariable long question_id){
        Collection<AnswerOptionDTO> answerOptionDTOs = answerOptionMapper.answerOptionToAnswerOptionDto(answerOptionService.findByQuestionId(question_id));
        return ResponseEntity.ok(answerOptionDTOs);
    }

    /*
     * http://localhost:8080/api/v1/answerOption/add?item=test1&id=1
     * */

    @PostMapping(path="/add")
    public  AnswerOptionDTO addAnswerOption(@RequestBody AnswerOptionDTO answerOptionDTO){
        AnswerOption answerOption = answerOptionService.add(answerOptionMapper.answerOptionDtoToAnswerOption(answerOptionDTO));
        return answerOptionMapper.answerOptionToAnswerOptionDto(answerOption);
    }

    @PatchMapping("/update/{id}")
    public AnswerOptionDTO updateAnswerOption (@RequestBody AnswerOptionDTO answerOptionDTO,@PathVariable long id){
        AnswerOption answerOption = answerOptionMapper.answerOptionDtoToAnswerOption(answerOptionDTO);
        return answerOptionMapper.answerOptionToAnswerOptionDto(answerOptionService.update(id, answerOption));
    }

    /*
     *http://localhost:8080/api/v1/answerOption/delete/37
     * */

    @DeleteMapping(path = "/delete/{answer_op_id}")
    @ResponseStatus(value = HttpStatus.OK) //200
    public  String deleteAnswerOption(@PathVariable("answer_op_id") long id) {

        List<Answer> answers = answerRepository.findByAnswerOptionId(id);
        if (!answers.isEmpty()) {
            return "YOU_CANNOT_DELETE_BECAUSE_USER_ALREADY_ANSWERED";
        }
        answerOptionService.deleteById(id);
        return "DELETE_SUCCESSFULLY";

    }

}
