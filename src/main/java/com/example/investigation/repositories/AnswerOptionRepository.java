package com.example.investigation.repositories;

import com.example.investigation.models.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption,Long> {
    List<AnswerOption> findById (long id);
    List<AnswerOption> findByItem(String item);
    List<AnswerOption> findByQuestionId(long question_id);

}
