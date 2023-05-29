package com.example.investigation.repositories;

import com.example.investigation.models.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    //List<Patient> findAllById(long id);

    Optional<Patient> findById(Long id);
    @Modifying
    @Query//("select p from Patient p where p.fullName like %?1%")
    ("select p from Patient p where LOWER (p.fullName) like LOWER(concat('%', ?1, '%'))")
    List<Patient> findAllByFullName(String fullName);
    List<Patient> findBySocialNumber(String socialNumber);
    List<Patient> findByEmail(String email);


}
