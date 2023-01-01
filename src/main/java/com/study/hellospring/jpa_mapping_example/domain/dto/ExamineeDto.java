package com.study.hellospring.jpa_mapping_example.domain.dto;

import com.study.hellospring.jpa_mapping_example.domain.entity.Examinee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@ToString
public class ExamineeDto {
    private Long id;
    private String name;
    private int age;
    private List<String> academyNames;

    public static ExamineeDto of(Examinee examinee) {
        return ExamineeDto.builder()
                .id(examinee.getId())
                .name(examinee.getName())
                .age(examinee.getAge())
                .academyNames(examinee.getExamineeAcademies().stream().map(list -> {
                    return list.getAcademy().getName();
                }).collect(Collectors.toList()))
                .build();
    }
}
