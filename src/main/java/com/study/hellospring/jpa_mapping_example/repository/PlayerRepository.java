package com.study.hellospring.jpa_mapping_example.repository;

import com.study.hellospring.jpa_mapping_example.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
