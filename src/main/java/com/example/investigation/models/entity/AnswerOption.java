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
//@Table(name="answer_option")
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
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_option_id"))
    private Question question;

    @OneToMany(mappedBy="answer")
    private List<Answer> answers;


}
