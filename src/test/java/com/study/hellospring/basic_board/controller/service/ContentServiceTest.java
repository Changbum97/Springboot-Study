package com.study.hellospring.basic_board.controller.service;

import com.study.hellospring.basic_board.domain.Content;
import com.study.hellospring.basic_board.service.ContentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ContentServiceTest {

    @Autowired
    ContentService contentService;

    Content content = null;

    @BeforeEach
    void beforeEach() {
        // 테스트를 시작 할 때 마다 content를 새로 생성
        // id와 updateDate는 자동 생성
        content = new Content();
        content.setTitle("글 제목");
        content.setWriter("작성자1");
        content.setPassword("1234");
        content.setTexts("글 내용");
    }

    @AfterEach
    void afterEach() {
        // 테스트가 끝날 때 마다 글 전체 삭제
        contentService.deleteAllContent();
    }

    @Test
    @DisplayName("1. 글 쓰기 테스트")
    void addTest() {
        contentService.writeContent(content);
        assertThat(contentService.getContentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("2. 글 보기(불러오기) 테스트")
    void getTest() {
        contentService.writeContent(content);

        Content findContent = contentService.getContent(1);

        assertThat(findContent.getTitle()).isEqualTo(content.getTitle());
        assertThat(findContent.getWriter()).isEqualTo(content.getWriter());
        assertThat(findContent.getPassword()).isEqualTo(content.getPassword());
        assertThat(findContent.getTexts()).isEqualTo(content.getTexts());
    }

    @Test
    @DisplayName("3. 글 수정 테스트")
    void editTest() {
        content.setTexts("원래 내용");
        contentService.writeContent(content);

        contentService.editContent(1, "수정 내용", "1");
        assertThat(contentService.getContent(1).getTexts()).isEqualTo("원래 내용");

        contentService.editContent(1, "수정 내용", "1234");
        assertThat(contentService.getContent(1).getTexts()).isEqualTo("수정 내용");
    }

    @Test
    @DisplayName("4. 전체 글 조회 테스트")
    void getAllTest() {
        // given
        contentService.writeContent(new Content());
        contentService.writeContent(new Content());
        contentService.writeContent(new Content());

        // when
        List<Content> contents = contentService.getAllContents();

        // then
        assertThat(contents.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("5. 글 삭제 테스트")
    void deleteTest() {
        contentService.writeContent(content);
        assertThat(contentService.getContentCount()).isEqualTo(1);

        contentService.deleteContent(1, "");
        assertThat(contentService.getContentCount()).isEqualTo(1);

        contentService.deleteContent(1, "1234");
        assertThat(contentService.getContentCount()).isEqualTo(0);
    }

}