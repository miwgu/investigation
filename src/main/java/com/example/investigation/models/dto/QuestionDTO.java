package com.example.investigation.models.dto;

import com.example.investigation.models.Survey;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class QuestionDTO {
    private long id;
    private long num;
    private String text;
    private long survey;

}
