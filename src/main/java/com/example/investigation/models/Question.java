package com.example.investigation.models;

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
    private String text;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="survey_id", referencedColumnName = "id")
    private Survey survey;


    public Question(){}

    public Question(String text, Survey survey) {
        this.text = text;
        this.survey = survey;
    }
}