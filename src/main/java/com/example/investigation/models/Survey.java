package com.example.investigation.models;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
/*
    public Survey(){}

    public Survey(String name) {
        this.name = name;
    }

 */

}