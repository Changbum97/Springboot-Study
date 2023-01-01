package com.study.hellospring.circular_reference_example.repository;

import com.study.hellospring.circular_reference_example.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
