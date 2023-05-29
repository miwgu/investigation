package com.example.investigation.models.entity;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "answer_patient",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Patient patient;


    @ManyToOne
    @JoinTable(name = "answer_answer_option",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_option_id"))
    private AnswerOption answerOption;

}
