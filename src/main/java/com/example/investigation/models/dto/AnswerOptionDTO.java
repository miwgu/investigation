package com.example.investigation.models.dto;

import com.example.investigation.models.Answer;
import lombok.Data;

import java.util.List;

@Data
public class AnswerOptionDTO {
    private long id;
    private String item;
    private long question;

    //private List<Answer> answers;

}
