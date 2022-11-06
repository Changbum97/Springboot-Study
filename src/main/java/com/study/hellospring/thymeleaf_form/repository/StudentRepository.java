package com.study.hellospring.thymeleaf_form.repository;

import com.study.hellospring.thymeleaf_form.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    private Map<Integer, Student> students = new HashMap<>();
    private int sequence = 0;

    public int save(Student student) {
        student.setId(++ sequence);
        students.put(student.getId(), student);
        return student.getId();
    }

    public Student findById(int id) {
        return students.get(id);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}
