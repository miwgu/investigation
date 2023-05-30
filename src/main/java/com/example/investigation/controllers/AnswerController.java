package com.example.investigation.controllers;

import com.example.investigation.mappers.AnswerMapper;
import com.example.investigation.mappers.AnswerOptionMapper;
import com.example.investigation.mappers.PatientMapper;
import com.example.investigation.models.dto.AnswerOptionDTO;
import com.example.investigation.models.dto.PatientDTO;
import com.example.investigation.models.entity.Answer;
import com.example.investigation.models.dto.AnswerDTO;
import com.example.investigation.repositories.AnswerRepository;
import com.example.investigation.services.answer.AnswerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/answer")
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerController {


    private final AnswerMapper answerMapper;
    private final AnswerService answerService;

    private final PatientMapper patientMapper;
    private final AnswerOptionMapper answerOptionMapper;


    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @GetMapping("/all")
    public ResponseEntity getAll() {
        Collection<AnswerDTO> answers = answerMapper.answerToAnswerDto(answerService.findAll());
        return ResponseEntity.ok(answers);
    }

    /*
     * http://localhost:8080/api/v1/answer/search/byId/1
     * */

    @GetMapping("search/byId/{id}")
    public ResponseEntity<AnswerDTO> getById(@PathVariable long id) {
        AnswerDTO answerDTO = answerMapper.answerToAnswerDto(answerService.findById(id));
        return ResponseEntity.ok(answerDTO);
    }

    /*
     * http://localhost:8080/api/v1/answer/search/byPatientId/1
     * */
    @GetMapping("search/byPatientId/{patient_id}")
    public ResponseEntity<Collection<AnswerDTO>> getAnswersByPatientId(@PathVariable long patient_id) {
        Collection<AnswerDTO> answerDTOS = answerMapper.answerToAnswerDto(answerService.findAllByPatientId(patient_id));
        return ResponseEntity.ok(answerDTOS);
    }


    /*
     * http://localhost:8080/api/v1/answer/search/byAnswerOptionId/1
     * */
    @GetMapping("search/byAnswerOptionId/{ao_id}")
    public ResponseEntity<Collection<AnswerDTO>> getAnswersByAOId(@PathVariable long ao_id) {
        Collection<AnswerDTO> answerDTOS = answerMapper.answerToAnswerDto(answerService.findAllByAnswerOptionId(ao_id));
        return ResponseEntity.ok(answerDTOS);
    }

    /*
     * http://localhost:8080/api/v1/answer/add/{p_id}/{ao_id}
     * */

//    @PostMapping("/add/{p_id}/{ao_id}")
//    public AnswerDTO addAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable Long p_id, @RequestBody Long ao_id){
//        Answer toAnswer = answerMapper.answerDtoToAnswer(answerDTO);
//        Answer answer = answerService.add(toAnswer,p_id, ao_id);
//        return answerMapper.answerToAnswerDto(answer);
//    }
//    @PostMapping("/add")
//    public AnswerDTO addAnswer(@RequestBody Answer answer){
//        //Answer toAnswer = answerMapper.answerDtoToAnswer(answerDTO);
//        //Answer answer = answerService.create(toAnswer);
//        return answerMapper.answerToAnswerDto(answer);
//    }

    /*
     * http://localhost:8080/api/v1/answer/add
     * */


    @PostMapping("/add")
    public AnswerDTO addAnswer(@RequestBody PatientDTO patientDTO, @RequestBody AnswerOptionDTO answerOptionDTO){
        Answer answer = answerService.add(patientMapper.patientDtoToPatient(patientDTO).getId(), answerOptionMapper.answerOptionDtoToAnswerOption(answerOptionDTO).getId());
        return answerMapper.answerToAnswerDto(answer);
    }


/*
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AnswerDTO addAnswer(@RequestBody AnswerDTO answerDTO){
        Answer answer = answerService.add(answerMapper.answerDtoToAnswer(answerDTO));
        return answerMapper.answerToAnswerDto(answer);
    }

 */



    /*
     * http://localhost:8080/api/v1/answer/search/bySurveyId/2
     * */

    //TODO: does not work so I need check how to write query in Repository or I just use jdbcTemplate
/*
    @GetMapping("/search/bySurveyId/{survey_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Map<String, Object>> getBySurveyId(@PathVariable long survey_id) {
        return answerService.findBySurveyId(survey_id);
    }

 */

    /*
     * http://localhost:8080/api/v1/answer/getPatientidQuestionAnswerBySurveyId/2
     *
     *  */

    @GetMapping("/getPatientidQuestionAnswerBySurveyId/{id}")
    List<Map<String, Object>> getBySurveyId(@PathVariable long id) {

        final String sql =
                "SELECT answer.patient_id, question.text as question, ao.item as answer " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where survey.id=:id ";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        List<Map<String, Object>> json = null;

        try {
            json = jdbcTemplate.queryForList(sql, param);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return json;
    }

    /*
     * http://localhost:8080/api/v1/answer/getQuestionAnswerBySurveyId/2/BypatientId/6
     *
     *  */

    @GetMapping("/getQuestionAnswerBySurveyId/{s_id}/BypatientId/{p_id}")
    List<Map<String, Object>> getBySurveyIdAndPatientId(@PathVariable long s_id, @PathVariable long p_id) {

        final String sql =
                "SELECT answer.patient_id , question.text as question, ao.item as answer " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where survey.id=:s_id AND answer.patient_id =:p_id ";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("s_id", s_id)
                .addValue("p_id",p_id);

        List<Map<String, Object>> json = null;

        try {
            json = jdbcTemplate.queryForList(sql, param);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return json;
    }


    /*
     * http://localhost:8080/answer/getAllAnswersWithPatientId
     * */
    /*
    @GetMapping("/getAllAnswersWithPatientId")
    List<Map<String, Object>> getQuestionAnswersWithPatient(){

        final String sql=
                "SELECT "+
                "t1.patient_id as patient_id, "+
                "t1.q1 as q1, "+
                "t2.q2 as q2, "+
                "t3.q3 as q3, "+
                "t4.q4 as q4, "+
                "t5.q5 as q5 "+

        "FROM (SELECT answer.patient_id as patient_id, ao.item as q1 "+
                        "FROM answer " +
                "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                "JOIN question ON question.id =ao.question_id "+
                "JOIN survey ON survey.id =question.survey_id "+
                "Where question_id=1 "+
                "AND survey.name='Psoriasis -Grundläggande') as t1 "+

        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q2 "+
                "FROM answer "+
                "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                "JOIN question ON question.id =ao.question_id "+
                "JOIN survey ON survey.id =question.survey_id "+
                "Where question_id=2 "+
                "AND survey.id=1) as t2 "+
        "ON t2.patient_id = t1.patient_id "+

        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q3 "+
                "FROM answer "+
                "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                "JOIN question ON question.id =ao.question_id "+
                "JOIN survey ON survey.id =question.survey_id "+
                "Where question_id=3 "+
                "AND survey.id=1) as t3 "+
        "ON t3.patient_id = t2.patient_id "+

        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q4 "+
                "FROM answer "+
                "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                "JOIN question ON question.id =ao.question_id "+
                "JOIN survey ON survey.id =question.survey_id "+
                "Where question_id=4 "+
                "AND survey.id=1) as t4 "+
        "ON t4.patient_id = t3.patient_id "+

        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q5 "+
                "FROM answer "+
                "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                "JOIN question ON question.id =ao.question_id "+
                "JOIN survey ON survey.id =question.survey_id "+
                "Where question_id=5 "+
                "AND survey.id=1) as t5 "+
        "ON t5.patient_id = t4.patient_id ";

        SqlParameterSource param = new MapSqlParameterSource();

        List<Map<String,Object>> json = null;

        try {
            json= jdbcTemplate.queryForList(sql,param);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return json;
    }

     */


    /*
     * http://localhost:8080/api/v1/answer/getAllAnswersWithPatientIdBySurveyId/1
     * */

    @GetMapping("/getAllAnswersWithPatientIdBySurveyId/{id}")
    List<Map<String, Object>> getQuestionAnswersPsoriasisBySurveyId(@PathVariable long id) {

        final String sql =
                "SELECT " +
                        "t1.patient_id as patient_id, " +
                        "t1.q1 as q1, " +
                        "t2.q2 as q2, " +
                        "t3.q3 as q3, " +
                        "t4.q4 as q4, " +
                        "t5.q5 as q5, " +
                        "t6.q6 as q6, " +
                        "t7.q7 as q7 " +

                        "FROM (SELECT answer.patient_id as patient_id, ao.item as q1 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=1 " +
                        "AND survey.id=:id) as t1 " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q2 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=2 " +
                        "AND survey.id=:id) as t2 " +
                        "ON t2.patient_id = t1.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q3 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=3 " +
                        "AND survey.id=:id) as t3 " +
                        "ON t3.patient_id = t2.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q4 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=4 " +
                        "AND survey.id=:id) as t4 " +
                        "ON t4.patient_id = t3.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q5 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=5 " +
                        "AND survey.id=:id) as t5 " +
                        "ON t5.patient_id = t4.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q6 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=6 " +
                        "AND survey.id=:id) as t6 " +
                        "ON t6.patient_id = t5.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q7 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=7 " +
                        "AND survey.id=:id) as t7 " +
                        "ON t7.patient_id = t6.patient_id ";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        List<Map<String, Object>> json = null;

        try {
            json = jdbcTemplate.queryForList(sql, param);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return json;
    }



    /*
     * http://localhost:8080/answer/getAllAnswersWithPatientIdBySurveyId2/2
     * */

    @GetMapping("/getAllAnswersWithPatientIdBySurveyId2/{id}")
    List<Map<String, Object>> getQuestionAnswersCancerBySurveyId(@PathVariable long id) {

        final String sql =
                "SELECT " +
                        "t1.patient_id as patient_id, " +
                        "t1.q1 as q1, " +
                        "t2.q2 as q2, " +
                        "t3.q3 as q3, " +
                        "t4.q4 as q4, " +
                        "t5.q5 as q5, " +
                        "t6.q6 as q6, " +
                        "t7.q7 as q7 " +

                        "FROM (SELECT answer.patient_id as patient_id, ao.item as q1 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=8 " +
                        "AND survey.id=:id) as t1 " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q2 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=9 " +
                        "AND survey.id=:id) as t2 " +
                        "ON t2.patient_id = t1.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q3 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=10 " +
                        "AND survey.id=:id) as t3 " +
                        "ON t3.patient_id = t2.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q4 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=11 " +
                        "AND survey.id=:id) as t4 " +
                        "ON t4.patient_id = t3.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q5 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=12 " +
                        "AND survey.id=:id) as t5 " +
                        "ON t5.patient_id = t4.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q6 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=13 " +
                        "AND survey.id=:id) as t6 " +
                        "ON t6.patient_id = t5.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q7 " +
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id " +
                        "JOIN question ON question.id =ao.question_id " +
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where question_id=14 " +
                        "AND survey.id=:id) as t7 " +
                        "ON t7.patient_id = t6.patient_id ";


        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        List<Map<String, Object>> json = null;

        try {
            json = jdbcTemplate.queryForList(sql, param);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return json;
    }


}

