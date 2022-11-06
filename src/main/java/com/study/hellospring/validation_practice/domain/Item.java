package com.study.hellospring.validation_practice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private String sellerEmail;
    private int price;
    private int quantity;
}
