package com.example.investigation.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long num;
    private String text;
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne // delete (cascade=CascadeType.ALL) It works by using hibernate @OnDelete annotation.
    @JoinColumn(name="survey_id", referencedColumnName = "id")
    private Survey survey;

/*
    public Question(){}

    public Question(long num, String text, Survey survey) {
        this.num = num;
        this.text = text;
        this.survey = survey;
    }

 */
}