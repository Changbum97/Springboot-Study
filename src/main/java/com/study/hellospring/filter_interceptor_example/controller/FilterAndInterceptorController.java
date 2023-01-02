package com.study.hellospring.filter_interceptor_example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/filter-interceptor-test")
public class FilterAndInterceptorController {

    @GetMapping("/all-pass")
    public String allPass(HttpServletRequest request) {
        log.info("REQUEST [{}][{}]", request.getAttribute("logId"), request.getRequestURI());
        return "filter_interceptor_example/filterAndInterceptorPassPage";
    }
}
