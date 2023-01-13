package com.study.hellospring.pagination_sort_example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {

    NO("정렬 안함"),
    AGE_DESC("나이 내림차순"),
    AGE_ASC("나이 오름차순"),
    RANK_DESC("랭크 내림차순"),
    RANK_ASC("랭크 오름차순");

    private final String description;

}
