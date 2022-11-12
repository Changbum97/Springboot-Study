package com.study.hellospring.jparepository_example.domain.dto;

import lombok.Data;

@Data
public class MemberRegisterDto {

    private String name;
    private int age;
    private Boolean isMale;
}
