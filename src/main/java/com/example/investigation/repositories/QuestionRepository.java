package com.example.investigation.repositories;

import com.example.investigation.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllById(long id);
    List<Question> findByText(String text);
    List<Question> findBySurveyId(long survey_id);
    Question findById(long id);
    //Question findByNumAndText(long num, String text);
    //void deleteByNumAndText(long num, String text);
}
