package com.study.hellospring.circular_reference_example.controller;

import com.study.hellospring.circular_reference_example.domain.dto.BookDto;
import com.study.hellospring.circular_reference_example.domain.entity.Author;
import com.study.hellospring.circular_reference_example.domain.entity.Book;
import com.study.hellospring.circular_reference_example.repository.AuthorRepository;
import com.study.hellospring.circular_reference_example.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/circular-referance")
@RequiredArgsConstructor
public class CirularReferanceController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @PostMapping("/author")
    public String makeAuthor() {
        Author author1 = new Author();
        author1.setName("저자1");
        author1.setAge(30);
        authorRepository.save(author1);
        return "저자 등록 완료";
    }

    @PostMapping("/book")
    public String makeBook() {
        Book book1 = new Book();
        book1.setName("책1");
        book1.setPrice(20000);
        book1.setAuthor(authorRepository.findById(1L).get());
        bookRepository.save(book1);
        return "책 등록 완료";
    }

    /*  순환 참조 해결 방법 1, 2에서 사용
    @GetMapping("/book/{bookId}")
    public Book showBook(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        return book;
    }*/

    @GetMapping("/book/{bookId}")
    public BookDto showBook(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        return BookDto.of(book);
    }
}
