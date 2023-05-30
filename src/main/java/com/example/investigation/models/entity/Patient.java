package com.example.investigation.models.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column()
    private  Long id;
    //    @Column(length = 100, nullable = false)
    private String socialNumber;
    //    @Column(length = 100, nullable = false)
    private String fullName;
    //    @Column(length = 100, nullable = false)
    private String email;

}