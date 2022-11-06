package com.study.hellospring.basic_board.domain;

import lombok.Data;

@Data
public class Content {

    private int id;
    private String title;
    private String texts;

    private String writer;
    private String password;

    private String updateDate;

}
