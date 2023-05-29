package com.example.investigation.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyDTO {
    private long id;
    private String name;
    private List<Long> questions;

}
