package com.study.hellospring.pagination_sort_example.domain;

import lombok.Data;

@Data
public class SearchForm {
    private Integer ageGe;
    private Integer ageLe;

    private Rank rankGe;
    private Rank rankLe;

    private SortType sortType;
}
