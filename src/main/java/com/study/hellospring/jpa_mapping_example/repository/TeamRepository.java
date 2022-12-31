package com.study.hellospring.jpa_mapping_example.repository;

import com.study.hellospring.jpa_mapping_example.domain.entity.Player;
import com.study.hellospring.jpa_mapping_example.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
