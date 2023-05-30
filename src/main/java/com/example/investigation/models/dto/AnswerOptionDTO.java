package com.example.investigation.models.dto;

import lombok.Data;

@Data
public class AnswerOptionDTO {
    private Long id;
    private String item;
    private Long question;

    //private List<Answer> answers;

}
