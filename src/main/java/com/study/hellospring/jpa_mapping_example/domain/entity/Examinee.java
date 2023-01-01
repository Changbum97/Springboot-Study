package com.study.hellospring.jpa_mapping_example.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Examinee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examinee_id")
    private Long id;

    private String name;
    private int age;

    // 연관관계 매핑
    @OneToMany(mappedBy = "examinee")
    private List<ExamineeAcademy> examineeAcademies = new ArrayList<>();
}
