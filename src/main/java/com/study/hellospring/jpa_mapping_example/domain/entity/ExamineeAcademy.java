package com.study.hellospring.jpa_mapping_example.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ExamineeAcademy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examinee_academy_id")
    private Long id;

    private LocalDateTime registerDate;

    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examinee_id")
    private Examinee examinee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy;
}
