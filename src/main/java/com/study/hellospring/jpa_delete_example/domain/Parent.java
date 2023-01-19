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
// Soft Delete 시 사용
//@SQLDelete(sql = "UPDATE parent SET deleted_at = current_timestamp WHERE id = ?")
//@Where(clause = "deleted_at is null")
public class Parent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

//    Soft Delete 시 사용
//    private LocalDateTime deletedAt;


//    CascadeType.REMOVE와 orphanRemoval = true 차이 비교 시 사용
//    @OneToMany(mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.PERSIST)
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)

//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)

    @OneToMany(mappedBy = "parent")
    List<Child> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grand_parent_id")
    private GrandParent grandParent;

}
