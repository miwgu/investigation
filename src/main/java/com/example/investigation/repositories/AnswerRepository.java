package com.example.investigation.repositories;

import com.example.investigation.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findById(long id);
    List <Answer> findByPatientId(long patient_id);
    List<Answer> findByAnswerOptionId(long answer_op_id);
}
