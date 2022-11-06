package com.study.hellospring.validation_practice.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddItemForm {

    @NotBlank(message = "상품명이 비어있을 수 없습니다!")
    @Length(max = 10, message = "상품명의 최대 길이는 10글자 입니다!")
    private String name;

    @NotBlank
    @Email
    private String sellerEmail;

    @NotNull
    @Range(min = 100, max = 1000000)
    private int price;

    @NotNull
    @Max(999)
    private int quantity;
}
