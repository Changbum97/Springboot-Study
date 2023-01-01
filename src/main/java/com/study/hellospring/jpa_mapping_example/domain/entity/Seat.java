package com.study.hellospring.jpa_mapping_example.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    private String rowId;
    private Integer colId;

    // 연관관계 매핑
    @OneToOne(mappedBy = "seat", fetch = FetchType.LAZY)
    private Customer customer;
}
