package com.example.investigation.models.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String item;
    @OnDelete(action = OnDeleteAction.CASCADE)

    @ManyToOne
    @JoinTable(name = "answeroption_question",
            joinColumns = @JoinColumn(name = "answer_option_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Question question;

    @OneToMany(mappedBy = "answerOption")
    private List<Answer> answers;


}
