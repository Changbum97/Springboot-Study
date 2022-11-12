package com.study.hellospring.jparepository_example.controller;

import com.study.hellospring.jparepository_example.domain.dto.MemberRegisterDto;
import com.study.hellospring.jparepository_example.domain.entity.Member;
import com.study.hellospring.jparepository_example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jparepository-example/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    // Member 등록
    @PostMapping("")
    public String register(MemberRegisterDto registerDto) {
        System.out.println(registerDto);
        Member member = new Member();
        member.setName(registerDto.getName());
        member.setAge(registerDto.getAge());
        member.setIsMale(registerDto.getIsMale());

        Member registeredMember = memberRepository.save(member);
        return registeredMember.getId() + "번 멤버 등록 완료";
    }

    // Member 수정
    @PutMapping("/{id}")
    public String edit(@PathVariable Long id, MemberRegisterDto registerDto) {
        //Member member = memberRepository.findById(id).get();
        Member member = new Member();
        member.setId(id);
        member.setName(registerDto.getName());
        member.setAge(registerDto.getAge());
        member.setIsMale(registerDto.getIsMale());

        memberRepository.save(member);
        return member.getId() + "번 멤버 수정 완료";
    }

    // findById
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {

        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()) {
            return id + "번 멤버가 없습니다.";
        }

        Member findMember = optionalMember.get();

        return findMember.toString();

    }

    // findAll
    @GetMapping("/all")
    public String findAll() {
        List<Member> members = memberRepository.findAll();
        return members.toString();
    }

    // delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()) {
            return id + "번 멤버가 없습니다.";
        }

        Member findMember = optionalMember.get();
        memberRepository.delete(findMember);
        return id + "번 멤버 삭제 완료";
    }

    // findByName
    @GetMapping("/name/{name}")
    public String findByName(@PathVariable String name) {
        return (memberRepository.findByName(name)).toString();
    }

    // findAllByIsMale
    @GetMapping("/isMale/{isMale}")
    public String findByIsMale(@PathVariable Boolean isMale) {
        return (memberRepository.findAllByIsMale(isMale)).toString();
    }

    // findAllByAge
    @GetMapping("/age")
    public String findByIsMale(@RequestParam int minAge, int maxAge) {
        return (memberRepository.findByAgeGreaterThanEqualAndAgeLessThanEqual(minAge, maxAge)).toString();
    }
}
