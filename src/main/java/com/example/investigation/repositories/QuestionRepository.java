package com.example.investigation.repositories;

import com.example.investigation.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    //List<Question> findAllById(long id);

    Optional<Question> findById (Long id);
    @Modifying
    @Query("select q from Question q where LOWER (q.text) like LOWER(concat('%', ?1, '%'))")
    List<Question> findAllByText(String text);

    List<Question> findByNum(long num);
    List<Question> findBySurveyId(long survey_id);
    //void deleteById(long id);

    //Question findById(long id);
    //Question findByNumAndText(long num, String text);
}
