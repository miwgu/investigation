package com.example.investigation.models;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="answer_option")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String item;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="question_id", referencedColumnName = "id")
    private Question question;

    public AnswerOption(){}
}
