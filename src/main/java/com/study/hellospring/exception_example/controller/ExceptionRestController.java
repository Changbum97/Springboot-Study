package com.study.hellospring.exception_example.controller;

import com.study.hellospring.exception_example.domain.ExceptionDto;
import com.study.hellospring.exception_example.domain.MyException;
import com.study.hellospring.exception_example.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/exception-example")
@RequiredArgsConstructor
public class ExceptionRestController {

    private final ExceptionService exceptionService;

    @GetMapping("/throw-my-exception/1")
    public void throwMyException1() {
        // ex) 로그인 시 username에 해당하는 User가 없는 경우
        exceptionService.login();
    }

    @GetMapping("/throw-my-exception/2")
    public void throwMyException2() {
        // ex) 로그인 하지 않은 유저가 댓글을 작성하려는 경우
        exceptionService.writeComment();
    }

    @GetMapping("/throw-my-exception/3")
    public void throwMyException3() {
        // ex) 회원가입 시 username이 중복되는 경우
        exceptionService.join();
    }

    @GetMapping("/throw-my-exception/4")
    public void throwMyException4() {
        // ex) 댓글 추가 시 DB 에러가 발생한 경우
        exceptionService.editComment();
    }

    // @ExceptionHandler는 이 Controller에서 발생한 Exception에 대해서만 적용
    //@ExceptionHandler(MyException.class)
    public ResponseEntity<?> myExceptionHandler(MyException e) {
        e.printStackTrace();
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(new ExceptionDto(e.getErrorCode()));
    }
}
