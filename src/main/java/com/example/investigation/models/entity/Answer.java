package com.example.investigation.models.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private Patient patient;

    //@OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne// delete (cascade=CascadeType.ALL) It works by using hibernate @OnDelete annotation.
    @JoinColumn(name="answer_op_id", referencedColumnName = "id")
    private AnswerOption answerOption;
/*
    public Answer(){}

 */

}
