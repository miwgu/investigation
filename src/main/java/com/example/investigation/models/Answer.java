package com.example.investigation.models;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="answer_op_id", referencedColumnName = "id")
    private AnswerOption answerOption;

    public Answer(){}

}
