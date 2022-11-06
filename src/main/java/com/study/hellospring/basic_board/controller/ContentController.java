package com.study.hellospring.basic_board.controller;

import com.study.hellospring.basic_board.domain.Content;
import com.study.hellospring.basic_board.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic-board")
public class ContentController {

    private final ContentService contentService;

    // 홈 화면
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("contents", contentService.getAllContents());
        return "basic_board/home";
    }

    @GetMapping("/")
    public String redirectHome() {
        return "redirect:/basic-board";
    }

    // 글 쓰기 화면
    @GetMapping("/content/write")
    public String writePage() {
        return "basic_board/write-page";
    }

    // 글 쓰기
    @PostMapping("/content/write")
    public String writeContent(Content content) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
        content.setUpdateDate( formattedDate );

        contentService.writeContent(content);
        return "redirect:/basic-board";
    }

    // 글 보기 화면
    @GetMapping("/content/{id}")
    public String showContent(@PathVariable int id, Model model) {
        model.addAttribute("content", contentService.getContent(id));
        return "basic_board/content-page";
    }

    // 글 수정
    @PostMapping("/content/{id}")
    public String editContent(@PathVariable int id, Content content) {
        System.out.println(content);
        contentService.editContent(id, content.getTexts(), content.getPassword());
        return "redirect:/basic-board";
    }

    // 글 삭제
    @PostMapping("/content/delete/{id}")
    public String deleteContent(@PathVariable int id, Content content) {
        String password = content.getPassword();
        contentService.deleteContent(id, password);
        return "redirect:/basic-board";
    }
}
