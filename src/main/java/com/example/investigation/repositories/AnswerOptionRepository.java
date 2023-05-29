package com.example.investigation.repositories;

import com.example.investigation.models.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption,Long> {
    //List<AnswerOption> findAllById (long id);
    Optional <AnswerOption> findById(long id);
    @Modifying
    @Query("select ao from AnswerOption ao where LOWER (ao.item) like LOWER(concat('%', ?1, '%'))")
    List<AnswerOption> findAllByItem(String item);
    List<AnswerOption> findByQuestionId(long question_id);


}
