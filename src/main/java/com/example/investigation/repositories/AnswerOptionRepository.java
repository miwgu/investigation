package com.example.investigation.repositories;

import com.example.investigation.models.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption,Long> {
    List<AnswerOption> findAllById (long id);
    List<AnswerOption> findByItem(String item);
    List<AnswerOption> findByQuestionId(long question_id);
    AnswerOption findById(long id);

}
