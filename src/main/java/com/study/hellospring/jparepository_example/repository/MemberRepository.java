package com.study.hellospring.jparepository_example.repository;

import com.study.hellospring.jparepository_example.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
    List<Member> findByAgeGreaterThanEqualAndAgeLessThanEqual(int minAge, int maxAge);
    List<Member> findAllByIsMale(Boolean isMale);
}
