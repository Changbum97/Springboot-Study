package com.study.hellospring.exception_example.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Controller
public class InitBinderModelAttributeController {

    @Data
    private static class InitBinderDto {
        private String name;
        private Date createdAt;
    }

    @PostMapping("/init-binder")
    @ResponseBody
    public String test(@RequestBody InitBinderDto dto) {
        log.info("name : {}", dto.getName());
        log.info("createdAt : {}", dto.getCreatedAt());
        return dto.toString();
    }

    @GetMapping("/model-attribute")
    public String home() {
        log.info("home 실행");
        return "exception_example/home.html";
    }

    @InitBinder()
    public void initBinder(WebDataBinder dataBinder) {
        log.info("Init Binder 실행");
        // String -> Date 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ModelAttribute
    public void addModel(Model model) {
        log.info("Model Attribute 실행");
        model.addAttribute("nowTime", LocalDateTime.now());
    }
}
