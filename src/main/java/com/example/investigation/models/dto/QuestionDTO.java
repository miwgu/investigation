package com.example.investigation.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private long id;
    private long num;
    private String text;
    private long survey;
    private List<Long> answerOptions;;

}
