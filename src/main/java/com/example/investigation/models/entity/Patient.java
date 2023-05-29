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

    private  long id;

    private String socialNumber;

    private String fullName;

    private String email;

    @OneToMany(mappedBy = "patient")
    private List<Answer>answers;



}