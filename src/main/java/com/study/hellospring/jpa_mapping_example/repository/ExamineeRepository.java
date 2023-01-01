package com.study.hellospring.jpa_mapping_example.repository;

import com.study.hellospring.jpa_mapping_example.domain.entity.Examinee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamineeRepository extends JpaRepository<Examinee, Long> {
}
