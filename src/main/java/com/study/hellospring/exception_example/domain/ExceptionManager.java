package com.study.hellospring.exception_example.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestControllerAdvice(annotations = Controller.class)
@RestControllerAdvice(basePackages = "com.study.hellospring.exception_example")
public class ExceptionManager {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> myExceptionHandler(MyException e) {
        e.printStackTrace();
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(new ExceptionDto(e.getErrorCode()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return "Runtime Exception 발생!";
    }
}
