package com.study.hellospring.entitymanager_example.controller;

import com.study.hellospring.entitymanager_example.domain.User;
import com.study.hellospring.entitymanager_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entitymanager-example/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public String create(@RequestParam String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        Long userId = userService.save(user);
        return userId + "번 유저 등록 완료";
    }

    @PutMapping("")
    public String update(@RequestParam Long id, String name, int age) {
        Long userId = userService.update(id, name, age);
        return userId + "번 유저 수정 완료";
    }

    @DeleteMapping("")
    public String delete(@RequestParam Long id) {
        userService.remove(id);
        return id + "번 유저 삭제 완료";
    }

    @GetMapping("")
    public String read(@RequestParam @Nullable Long id, String name) {
        if(id != null) {
            return userService.findById(id).toString();
        } else {
            return userService.findByName(name).toString();
        }
    }

    @GetMapping("/all")
    public String readAll() {
        return userService.findAll().toString();
    }
}
