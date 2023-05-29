package com.example.investigation.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column()
    private  long id;
//    @Column(length = 100, nullable = false)
    private String socialNumber;
//    @Column(length = 100, nullable = false)
    private String fullName;
//    @Column(length = 100, nullable = false)
    private String email;
    @OneToMany(mappedBy="patient")
    private List<Answer>answers;



}