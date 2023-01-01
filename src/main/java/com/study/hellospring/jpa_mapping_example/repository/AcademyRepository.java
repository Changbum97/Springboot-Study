package com.study.hellospring.jpa_mapping_example.repository;

import com.study.hellospring.jpa_mapping_example.domain.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademyRepository extends JpaRepository<Academy, Long> {
}
