package com.study.hellospring.pagination_sort_example.repository;

import com.study.hellospring.pagination_sort_example.domain.Gamer;
import com.study.hellospring.pagination_sort_example.domain.Rank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface GamerRepository extends JpaRepository<Gamer, Long> {
        Page<Gamer> findAll(Pageable pageable);
        Page<Gamer> findByAgeGreaterThanEqualAndAgeLessThanEqualAndRankGreaterThanEqualAndRankLessThanEqual(
                Integer ageGe, Integer ageLe, Rank rankGe, Rank rankLe, Pageable pageable);
    }
