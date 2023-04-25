package com.example.investigation.models.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private long id;
    private String socialNumber;
    private String fullName;
    private String email;

}
