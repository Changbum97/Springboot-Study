package com.study.hellospring.entitymanager_example.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private int age;
}
