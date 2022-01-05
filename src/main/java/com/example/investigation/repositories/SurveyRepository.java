package com.example.investigation.repositories;

import com.example.investigation.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
    Survey findAllById(long id);
    Survey findByName(String name);
}
