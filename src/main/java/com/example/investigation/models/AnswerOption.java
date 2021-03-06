package com.example.investigation.models;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne // delete (cascade=CascadeType.ALL) It works by using hibernate @OnDelete annotation.
    @JoinColumn(name="question_id", referencedColumnName = "id")
    private Question question;

    public AnswerOption(){}

    public AnswerOption(String item, Question question) {
        this.item = item;
        this.question = question;
    }

    public AnswerOption(long id, String item, Question question) {
        this.id = id;
        this.item = item;
        this.question = question;
    }
}
