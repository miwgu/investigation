package com.example.investigation.models.dto;

import com.example.investigation.models.Answer;
import lombok.Data;

import java.util.List;

@Data
public class PatientDTO {
    private long id;
    private String socialNumber;
    private String fullName;
    private String email;

    //private List<Answer> answers;

}
