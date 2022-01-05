package com.example.investigation.repositories;

import com.example.investigation.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findById(long id);
    List<Answer> findByPatientId(long patient_id);
}
