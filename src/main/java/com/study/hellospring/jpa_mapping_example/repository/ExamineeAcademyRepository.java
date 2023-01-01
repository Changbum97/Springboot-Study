package com.study.hellospring.jpa_mapping_example.repository;

import com.study.hellospring.jpa_mapping_example.domain.entity.ExamineeAcademy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamineeAcademyRepository extends JpaRepository<ExamineeAcademy, Long> {
}
