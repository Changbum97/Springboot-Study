package com.study.hellospring.validation_example.controller;

import com.study.hellospring.validation_example.domain.AddItemForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/validation")
public class ItemController {

    @GetMapping("/add")
    public String addItemForm(Model model) {
        model.addAttribute("addItemForm",new AddItemForm());
        return "validation_example/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Valid @ModelAttribute("addItemForm") AddItemForm form, BindingResult bindingResult) {

        // 글로벌 에러 처리
        Long totalPrice = Long.valueOf(form.getPrice() * form.getQuantity());
        if(totalPrice > 500000000) {
            bindingResult.reject("overMaxTotalPrice",
                    new Object[]{500000000, totalPrice},
                    "글로벌 에러 메세지");
        }

        // 모든 에러 처리
        if(bindingResult.hasErrors()) {
            log.info("상품 등록 실패");
            return "validation_example/addForm";
        }

        log.info("상품 등록 완료");
        return "redirect:/validation/add";
    }
}
