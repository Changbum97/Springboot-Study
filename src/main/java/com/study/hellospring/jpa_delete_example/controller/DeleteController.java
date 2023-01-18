package com.study.hellospring.jpa_delete_example.controller;

import com.study.hellospring.jpa_delete_example.domain.Child;
import com.study.hellospring.jpa_delete_example.domain.GrandParent;
import com.study.hellospring.jpa_delete_example.domain.Parent;
import com.study.hellospring.jpa_delete_example.repository.ChildRepository;
import com.study.hellospring.jpa_delete_example.repository.GrandParentRepository;
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
    private final GrandParentRepository grandParentRepository;

    @PostMapping("/all")
    public String makeInitData() {
        GrandParent Tom = GrandParent.builder().name("Tom").age(70).build();
        grandParentRepository.save(Tom);

        Parent Harry = Parent.builder().name("Harry").age(51).grandParent(Tom).build();
        Parent Jane = Parent.builder().name("James").age(45).grandParent(Tom).build();
        parentRepository.save(Harry);
        parentRepository.save(Jane);

        Child Alice = Child.builder().name("Alice").age(19).parent(Harry).build();
        Child John = Child.builder().name("Kelly").age(26).parent(Harry).build();
        Child Peter = Child.builder().name("Peter").age(18).parent(Jane).build();
        childRepository.save(Alice);
        childRepository.save(John);
        childRepository.save(Peter);

        return "등록 완료";
    }

    @GetMapping("/all")
    public String printAll() {
        String result = "<조부모 리스트>\n";

        List<GrandParent> grandParents = grandParentRepository.findAll();
        for (GrandParent grandParent : grandParents) {
            int totalChild = grandParent.getParents().size();
            for(Parent parent : grandParent.getParents()) {
                totalChild += parent.getChildren().size();
            }
            result += String.format("이름: %s      나이: %d    전체 자손 수 : %d\n",
                    grandParent.getName(), grandParent.getAge(), totalChild);
        }

        result += "\n<부모 리스트>\n";
        List<Parent> parents = parentRepository.findAll();
        for (Parent parent : parents) {
            result += String.format("이름: %s    나이: %d    부모 : %s   자식 수 : %d\n",
                    parent.getName(), parent.getAge(), parent.getGrandParent().getName(), parent.getChildren().size());
        }

        result += "\n<자식 리스트>\n";
        List<Child> children = childRepository.findAll();
        for (Child child : children) {
            result += String.format("이름: %s    나이: %d    부모 : %s   조부모 : %s\n",
                    child.getName(), child.getAge(), child.getParent().getName(), child.getParent().getGrandParent().getName());
        }

        return result;
    }

    @DeleteMapping("/grand-parent/{grandParentId}")
    public String deleteGrandParentId(@PathVariable Long grandParentId) {
        grandParentRepository.deleteById(grandParentId);
        return grandParentId + "번 조부모 삭제 완료";
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
