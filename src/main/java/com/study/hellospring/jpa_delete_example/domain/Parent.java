package com.study.hellospring.jpa_delete_example.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

//    @OneToMany(mappedBy = "parent")
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    List<Child> children = new ArrayList<>();

}
