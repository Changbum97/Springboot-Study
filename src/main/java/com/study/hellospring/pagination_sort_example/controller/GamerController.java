package com.study.hellospring.pagination_sort_example.controller;

import com.study.hellospring.pagination_sort_example.domain.Gamer;
import com.study.hellospring.pagination_sort_example.domain.Rank;
import com.study.hellospring.pagination_sort_example.domain.SearchForm;
import com.study.hellospring.pagination_sort_example.domain.SortType;
import com.study.hellospring.pagination_sort_example.repository.GamerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pagination-sort-example")
@RequiredArgsConstructor
public class GamerController {

    private final GamerRepository gamerRepository;

    @GetMapping("/gamers")
    public String getGamers(@RequestParam(required = false, defaultValue = "1") int page,
                            Model model, @ModelAttribute SearchForm form) {

        // 검색 조건
        if (form.getAgeGe() == null) form.setAgeGe(0);
        if (form.getAgeLe() == null) form.setAgeLe(999);
        if (form.getRankGe() == null) form.setRankGe(Rank.BRONZE);
        if (form.getRankLe() == null) form.setRankLe(Rank.DIAMOND);

        if (form.getAgeGe() > form.getAgeLe() || form.getRankGe().compareTo(form.getRankLe()) == 1) {
            model.addAttribute("message", "검색 조건 에러");
            model.addAttribute("nextUrl", "/pagination-sort-example/gamers");
            return "pagination_sort_example/message";
        }

        // 정렬 조건
        if (form.getSortType() == null) form.setSortType(SortType.ID_ASC);
        SortType sortType = form.getSortType();

        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        if (sortType == SortType.ID_ASC) pageRequest = PageRequest.of(page - 1, 7, Sort.by("id").ascending());
        else if (sortType == SortType.ID_DESC) pageRequest = PageRequest.of(page - 1, 7, Sort.by("id").descending());
        else if (sortType == SortType.AGE_ASC) pageRequest = PageRequest.of(page - 1, 7, Sort.by("age").ascending());
        else if (sortType == SortType.AGE_DESC) pageRequest = PageRequest.of(page - 1, 7, Sort.by("age").descending());
        else if (sortType == SortType.RANK_ASC) pageRequest = PageRequest.of(page - 1, 7, Sort.by("rank").ascending());
        else if (sortType == SortType.RANK_DESC) pageRequest = PageRequest.of(page - 1, 7, Sort.by("rank").descending());
        else {
            model.addAttribute("message", "정렬 조건 에러");
            model.addAttribute("nextUrl", "/pagination-sort-example/gamers");
            return "pagination_sort_example/message";
        }

        Page<Gamer> gamers =
                gamerRepository.findByAgeGreaterThanEqualAndAgeLessThanEqualAndRankGreaterThanEqualAndRankLessThanEqual(
                        form.getAgeGe(), form.getAgeLe(), form.getRankGe(), form.getRankLe(), pageRequest);

        model.addAttribute("gamers", gamers);
        model.addAttribute("searchForm", form);

        model.addAttribute("ranks", Rank.values());
        model.addAttribute("sortTypes", SortType.values());
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
        long totalElements = result.getTotalElements();
        boolean hasNextPage = result.hasNext();
        boolean hasPreviousPage = result.hasPrevious();
        boolean isFirstPage = result.isFirst();
        boolean isLastPage = result.isLast();

        System.out.println("페이지 정보");
        System.out.println("gamers : " + gamers.toString());
        System.out.println("현재 페이지 : " + nowPageNumber);
        System.out.println("전체 페이지 수 : " + totalPages);
        System.out.println("한 페이지에 출력되는 개수 : " + pageSize);
        System.out.println("전체 개수 : " + totalElements);
        System.out.println("다음 페이지 존재 여부 : " + hasNextPage);
        System.out.println("이전 페이지 존재 여부 : " + hasPreviousPage);
        System.out.println("첫번째 페이지 인가? : " + isFirstPage);
        System.out.println("마지막 페이지 인가? : " + isLastPage);

        return result;
    }

}
