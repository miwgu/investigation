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

    @ManyToOne (cascade = CascadeType.ALL)
//    @JoinTable(name = "answer_patient",
//            joinColumns = @JoinColumn(name = "answer_id"),
//            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private Patient patient;


    @ManyToOne (cascade = CascadeType.ALL)
//    @JoinTable(name = "answer_answer_option",
//            joinColumns = @JoinColumn(name = "answer_id"),
//            inverseJoinColumns = @JoinColumn(name = "answer_option_id"))
    @JoinColumn(name="answer_op_id",referencedColumnName = "id")
    private AnswerOption answerOption;

}
