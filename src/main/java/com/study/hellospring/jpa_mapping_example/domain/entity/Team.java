package com.study.hellospring.jpa_mapping_example.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String name;

    // 연관관계 매핑
    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();
}
