package com.study.hellospring.jpa_mapping_example.domain.dto;

import com.study.hellospring.jpa_mapping_example.domain.entity.Examinee;
import com.study.hellospring.jpa_mapping_example.domain.entity.ExamineeAcademy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@ToString
public class ExamineeAcademyDto {
    private String examineeName;
    private String academyName;
    private LocalDateTime registerDate;

    public static ExamineeAcademyDto of(ExamineeAcademy examineeAcademy) {
        return ExamineeAcademyDto.builder()
                .examineeName(examineeAcademy.getExaminee().getName())
                .academyName(examineeAcademy.getAcademy().getName())
                .registerDate(examineeAcademy.getRegisterDate())
                .build();
    }
}
