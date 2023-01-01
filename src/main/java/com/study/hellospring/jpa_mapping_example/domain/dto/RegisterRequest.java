package com.study.hellospring.jpa_mapping_example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {
    private Long examineeId;
    private Long academyId;
}
