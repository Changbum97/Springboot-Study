package com.study.hellospring.circular_reference_example.domain.dto;

import com.study.hellospring.circular_reference_example.domain.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 순환 참조 해결 방법 3 => DTO 활용
@Getter
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String name;
    private int price;
    private String authorName;

    public static BookDto of(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getPrice(), book.getAuthor().getName());
    }
}
