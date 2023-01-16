package com.study.hellospring.jpa_delete_example.controller;

import com.study.hellospring.jpa_delete_example.domain.Child;
import com.study.hellospring.jpa_delete_example.domain.Parent;
import com.study.hellospring.jpa_delete_example.repository.ChildRepository;
import com.study.hellospring.jpa_delete_example.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa-delete-example")
@RequiredArgsConstructor
public class DeleteController {

    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;

    @PostMapping("/all")
    public String makeInitData() {
        Parent parent = Parent.builder().name("Harry").age(51).build();
        parentRepository.save(parent);

        Child child1 = Child.builder().name("Alice").age(19).parent(parent).build();
        Child child2 = Child.builder().name("John").age(16).parent(parent).build();
        childRepository.save(child1);
        childRepository.save(child2);

        return "등록 완료";
    }

    @GetMapping("/all")
    public String printAll() {
        String result = "";

        List<Parent> parents = parentRepository.findAll();
        for (Parent parent : parents) {
            result += String.format("부모 이름: %s    나이: %d    자식 수 : %d\n",
                    parent.getName(), parent.getAge(), parent.getChildren().size());
        }

        List<Child> children = childRepository.findAll();
        for (Child child : children) {
            result += String.format("자식 이름: %s    나이: %d    부모 이름 : %s\n",
                    child.getName(), child.getAge(), child.getParent().getName());
        }

        return result;
    }

    @DeleteMapping("/parent/{parentId}")
    public String deleteParent(@PathVariable Long parentId) {
        parentRepository.deleteById(parentId);
        return parentId + "번 부모 삭제 완료";
    }


    @DeleteMapping("/child/{childId}")
    public String deleteChild(@PathVariable Long childId) {
        childRepository.deleteById(childId);
        return childId + "번 자식 삭제 완료";
    }
}
