package com.study.hellospring.converter_formatter_example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IpPort {

    private String ip;
    private int port;
}
