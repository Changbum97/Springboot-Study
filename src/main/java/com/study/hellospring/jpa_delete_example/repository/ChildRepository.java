package com.study.hellospring.jpa_delete_example.repository;

import com.study.hellospring.jpa_delete_example.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
