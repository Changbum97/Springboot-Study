package com.study.hellospring.circular_reference_example.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @OneToOne(mappedBy = "author",fetch = FetchType.LAZY)
    // @JsonManagedReference  // 순환 참조 해결 방법 2
    private Book book;

    // 순환 참조 해결 방법 4 => 단방향 매핑으로 수정
}
