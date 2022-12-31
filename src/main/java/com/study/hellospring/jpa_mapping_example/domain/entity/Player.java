package com.study.hellospring.jpa_mapping_example.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Player {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    private String name;
    private Integer age;

    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
