package com.study.hellospring.jpa_mapping_example.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    // 연관관계 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;
}
