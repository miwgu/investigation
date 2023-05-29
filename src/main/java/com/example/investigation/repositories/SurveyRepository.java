package com.example.investigation.repositories;

import com.example.investigation.models.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SurveyRepository extends JpaRepository<Survey,Long> {
    //Survey findById(long id);
    Optional<Survey> findById(Long id);
    @Modifying
    @Query
   ("select s from Survey s where LOWER (s.name) like LOWER(concat('%', ?1, '%'))")
    List<Survey> findAllByName(String name);

}
