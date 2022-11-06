package com.study.hellospring.thymeleaf_form.domain;

import lombok.Data;

import java.util.List;

@Data
public class Student {

    private int id;
    private String name;
    private int studentId;          // 학번
    private int age;
    private Boolean isMale;         // 성별 (true: 남성, false: 여성)
    private Grade grade;            // 학년
    private Boolean isAttending;    // 재학 여부
    private List<String> clubs;     // 참여 활동
    private String major;           // 전공
}
