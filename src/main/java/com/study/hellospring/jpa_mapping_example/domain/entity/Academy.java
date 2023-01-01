package com.study.hellospring.jpa_mapping_example.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Academy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academy_id")
    private Long id;

    private String name;

    // 연관관계 매핑
    @OneToMany(mappedBy = "academy")
    private List<ExamineeAcademy> examineeAcademies = new ArrayList<>();
}
