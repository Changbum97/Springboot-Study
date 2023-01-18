package com.study.hellospring.jpa_delete_example.repository;

import com.study.hellospring.jpa_delete_example.domain.GrandParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrandParentRepository extends JpaRepository<GrandParent, Long> {
}
