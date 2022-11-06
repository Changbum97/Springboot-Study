package com.study.hellospring.thymeleaf_form.controller;

import com.study.hellospring.thymeleaf_form.domain.Grade;
import com.study.hellospring.thymeleaf_form.domain.Student;
import com.study.hellospring.thymeleaf_form.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "thymeleaf_form/StudentList";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        Student student = new Student();
        student.setIsMale(  true);
        student.setGrade(Grade.Freshman);
        model.addAttribute("student", student);
        model.addAttribute("grades", Grade.values());

        Map<Integer, String> clubs = new LinkedHashMap<>();
        clubs.put(1, "중앙 동아리");
        clubs.put(2, "과 동아리");
        clubs.put(3, "연합 동아리");
        clubs.put(4, "과 학생회");
        clubs.put(5, "총 학생회");
        model.addAttribute("clubs", clubs);

        List<String> majors = new ArrayList<>();
        majors.add("컴퓨터공학부");
        majors.add("기계공학부");
        majors.add("전자전기공학부");
        model.addAttribute("majors", majors);

        return "thymeleaf_form/RegisterForm";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/student";
    }

    @GetMapping("/{id}")
    public String studentInfo(@PathVariable int id, Model model) {
        model.addAttribute("student", studentRepository.findById(id));
        System.out.println(studentRepository.findById(id));

        Map<Integer, String> clubs = new LinkedHashMap<>();
        clubs.put(1, "중앙 동아리");
        clubs.put(2, "과 동아리");
        clubs.put(3, "연합 동아리");
        clubs.put(4, "과 학생회");
        clubs.put(5, "총 학생회");
        model.addAttribute("clubs", clubs);

        return "thymeleaf_form/StudentInfo";
    }
}

