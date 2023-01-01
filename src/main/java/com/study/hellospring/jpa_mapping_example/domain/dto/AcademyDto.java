package com.study.hellospring.jpa_mapping_example.domain.dto;

import com.study.hellospring.jpa_mapping_example.domain.entity.Academy;
import com.study.hellospring.jpa_mapping_example.domain.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@ToString
public class AcademyDto {
    private Long id;
    private String name;
    private List<String> examineeNames;

    public static AcademyDto of(Academy academy) {
        return AcademyDto.builder()
                .id(academy.getId())
                .name(academy.getName())
                .examineeNames(academy.getExamineeAcademies().stream().map(list -> {
                    return list.getExaminee().getName();
                }).collect(Collectors.toList()))
                .build();
    }
}
