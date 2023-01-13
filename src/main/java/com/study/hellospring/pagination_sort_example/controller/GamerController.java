package com.study.hellospring.pagination_sort_example.controller;

import com.study.hellospring.pagination_sort_example.domain.Gamer;
import com.study.hellospring.pagination_sort_example.repository.GamerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pagination-sort-example")
@RequiredArgsConstructor
public class GamerController {

    private final GamerRepository gamerRepository;

    @GetMapping("/gamers")
    public String getGamers(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                            Model model) {
        Page<Gamer> gamers = gamerRepository.findAll(pageable);

        model.addAttribute("gamers", gamers);
        return "pagination_sort_example/home";
    }

    @ResponseBody
    @GetMapping("/print-pageable")
    public Page<Gamer> printPageable(@PageableDefault(size = 5, sort = "age", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Gamer> result = gamerRepository.findAll(pageable);

        List<Gamer> gamers = result.getContent();
        int nowPageNumber = result.getNumber();
        int totalPages = result.getTotalPages();
        int pageSize = result.getSize();
        boolean hasNextPage = result.hasNext();
        boolean hasPreviousPage = result.hasPrevious();
        boolean isFirstPage = result.isFirst();
        boolean isLastPage = result.isLast();

        System.out.println("페이지 정보");
        System.out.println("gamers : " + gamers.toString());
        System.out.println("현재 페이지 : " + nowPageNumber);
        System.out.println("전체 페이지 수 : " + totalPages);
        System.out.println("한 페이지에 출력되는 개수 : " + pageSize);
        System.out.println("다음 페이지 존재 여부 : " + hasNextPage);
        System.out.println("이전 페이지 존재 여부 : " + hasPreviousPage);
        System.out.println("첫번째 페이지 인가? : " + isFirstPage);
        System.out.println("마지막 페이지 인가? : " + isLastPage);

        return result;
    }

}
