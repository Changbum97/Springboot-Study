package com.study.hellospring.jpa_mapping_example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerAddRequest {
    private String name;
    private Integer age;
    private Long teamId;
}
