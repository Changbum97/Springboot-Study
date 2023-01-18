package com.study.hellospring.jpa_delete_example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GrandParent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

//    @OneToMany(mappedBy = "grandParent")
    @OneToMany(mappedBy = "grandParent", orphanRemoval = true)
//    @OneToMany(mappedBy = "grandParent", cascade = CascadeType.REMOVE)
    List<Parent> parents = new ArrayList<>();

}
