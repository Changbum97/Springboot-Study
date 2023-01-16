package com.study.hellospring.jpa_delete_example.repository;

import com.study.hellospring.jpa_delete_example.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
