package com.study.hellospring.jpa_delete_example.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
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

//    @OneToMany(mappedBy = "grandParent", orphanRemoval = true)
//    @OneToMany(mappedBy = "grandParent", cascade = CascadeType.REMOVE)
    @OneToMany(mappedBy = "grandParent")
    List<Parent> parents = new ArrayList<>();

}
