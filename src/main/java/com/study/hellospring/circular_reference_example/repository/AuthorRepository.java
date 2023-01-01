package com.study.hellospring.circular_reference_example.repository;

import com.study.hellospring.circular_reference_example.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
