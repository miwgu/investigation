package com.example.investigation.models.entity;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Builder
//@Table(name="answer")
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinTable(name = "answer_patient",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Patient patient;

    @ManyToOne
    @JoinTable(name = "answer_answeroption",
            joinColumns = @JoinColumn(name = "answer_option_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private AnswerOption answerOption;

}
