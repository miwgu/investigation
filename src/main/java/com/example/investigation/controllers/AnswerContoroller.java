package com.example.investigation.controllers;

import com.example.investigation.models.Answer;
import com.example.investigation.repositories.AnswerRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/answer")
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerContoroller {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Operation(summary = "Get all answers")
    @GetMapping("/all")
    public Iterable<Answer> getAllPatients() {
        return answerRepository.findAll();
    }

    @GetMapping("/byId/{id}")
    public Iterable<Answer> getAnswersById(@PathVariable long id) {
        return answerRepository.findById(id);
    }

    @GetMapping("/byPatientId/{patient_id}")
    public Iterable<Answer> getAnswersByPatientId(@PathVariable long patient_id) {
        return answerRepository.findByPatientId(patient_id);
    }


    /*
    * http://localhost:8080/api/v1/answer/getPatientidQuestionAnswerBySurveyName/2
    *
    *  */

    @GetMapping("/getPatientidQuestionAnswerBySurveyName/{id}")
    List<Map<String, Object>> getBySurveyName(@PathVariable long id){

        final String sql=
                "SELECT answer.patient_id, question.text, ao.item "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id " +
                        "Where survey.id=:id ";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

        List<Map<String,Object>> json = null;

        try {
            json= jdbcTemplate.queryForList(sql,param);
        } catch (EmptyResultDataAccessException e){
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
    List<Map<String, Object>> getQuestionAnswersPsoriasisGBySurveyId(@PathVariable long id){

        final String sql=
                "SELECT "+
                        "t1.patient_id as patient_id, "+
                        "t1.q1 as q1, "+
                        "t2.q2 as q2, "+
                        "t3.q3 as q3, "+
                        "t4.q4 as q4, "+
                        "t5.q5 as q5, "+
                        "t6.q6 as q6, "+
                        "t7.q7 as q7 "+

                        "FROM (SELECT answer.patient_id as patient_id, ao.item as q1 "+
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=1 "+
                        "AND survey.id=:id) as t1 "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q2 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=2 "+
                        "AND survey.id=:id) as t2 "+
                        "ON t2.patient_id = t1.patient_id "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q3 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=3 "+
                        "AND survey.id=:id) as t3 "+
                        "ON t3.patient_id = t2.patient_id "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q4 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=4 "+
                        "AND survey.id=:id) as t4 "+
                        "ON t4.patient_id = t3.patient_id "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q5 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=5 "+
                        "AND survey.id=:id) as t5 "+
                        "ON t5.patient_id = t4.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q6 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=6 "+
                        "AND survey.id=:id) as t6 "+
                        "ON t6.patient_id = t5.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q7 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=7 "+
                        "AND survey.id=:id) as t7 "+
                        "ON t7.patient_id = t6.patient_id ";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

        List<Map<String,Object>> json = null;

        try {
            json= jdbcTemplate.queryForList(sql,param);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return json;
    }



    /*
    * http://localhost:8080/answer/getAllAnswersWithPatientIdBySurveyId2/2
    * */

    @GetMapping("/getAllAnswersWithPatientIdBySurveyId2/{id}")
    List<Map<String, Object>> getQuestionAnswersCancerBySurveyId(@PathVariable long id){

        final String sql=
                "SELECT "+
                        "t1.patient_id as patient_id, "+
                        "t1.q1 as q1, "+
                        "t2.q2 as q2, "+
                        "t3.q3 as q3, "+
                        "t4.q4 as q4, "+
                        "t5.q5 as q5, "+
                        "t6.q6 as q6, "+
                        "t7.q7 as q7 "+

                        "FROM (SELECT answer.patient_id as patient_id, ao.item as q1 "+
                        "FROM answer " +
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=8 "+
                        "AND survey.id=:id) as t1 "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q2 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=9 "+
                        "AND survey.id=:id) as t2 "+
                        "ON t2.patient_id = t1.patient_id "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q3 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=10 "+
                        "AND survey.id=:id) as t3 "+
                        "ON t3.patient_id = t2.patient_id "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q4 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=11 "+
                        "AND survey.id=:id) as t4 "+
                        "ON t4.patient_id = t3.patient_id "+

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q5 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=12 "+
                        "AND survey.id=:id) as t5 "+
                        "ON t5.patient_id = t4.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q6 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=13 "+
                        "AND survey.id=:id) as t6 "+
                        "ON t6.patient_id = t5.patient_id " +

                        "JOIN (SELECT answer.patient_id as patient_id, ao.item as q7 "+
                        "FROM answer "+
                        "JOIN answer_option ao ON ao.id= answer.answer_op_id "+
                        "JOIN question ON question.id =ao.question_id "+
                        "JOIN survey ON survey.id =question.survey_id "+
                        "Where question_id=14 "+
                        "AND survey.id=:id) as t7 "+
                        "ON t7.patient_id = t6.patient_id ";





        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

        List<Map<String,Object>> json = null;

        try {
            json= jdbcTemplate.queryForList(sql,param);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return json;
    }


}

