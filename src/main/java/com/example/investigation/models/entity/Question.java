package com.example.investigation.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinTable(name = "question_survey",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Survey survey;
    @OneToMany(mappedBy= "answer_option")
    private List <AnswerOption> answerOptions;


}