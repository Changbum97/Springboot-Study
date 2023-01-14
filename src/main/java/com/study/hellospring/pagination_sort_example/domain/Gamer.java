package com.study.hellospring.pagination_sort_example.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Gamer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @Column(name = "\"rank\"")
    private Rank rank;
}
