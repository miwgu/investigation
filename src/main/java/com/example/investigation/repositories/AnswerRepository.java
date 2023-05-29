package com.example.investigation.repositories;

import com.example.investigation.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findById(long id);
    List <Answer> findByPatientId(long patient_id);
    List<Answer> findByAnswerOptionId(long answer_op_id);


    //@Modifying
  @Query("SELECT a.patient.id, q.text, ao.item "+
            "FROM Answer a " +
            "JOIN AnswerOption ao ON ao.id= a.answerOption.id "+
            "JOIN Question q ON q.id =ao.question.id "+
            "JOIN Survey s ON s.id =q.survey.id " +
            "Where s.id=:survey_id ")
    List<Map<String, Object>> findBySurveyId(long survey_id);
}


