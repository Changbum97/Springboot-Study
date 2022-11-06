package com.study.hellospring.basic_board.service;

import com.study.hellospring.basic_board.domain.Content;
import com.study.hellospring.basic_board.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    /**
     * 글 작성
     */
    public void writeContent(Content content) {
        contentRepository.save(content);
    }

    /**
     * 글 수정
     */
    public void editContent(int id, String texts, String password) {
        Content content = contentRepository.findById(id);
        if(!content.getPassword().equals(password)) {
            return;
        }

        content.setTexts(texts);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
        content.setUpdateDate(formattedDate);

        contentRepository.edit(id, content);
    }

    /**
     * 글 삭제
     */
    public void deleteContent(int id, String password) {
        Content content = contentRepository.findById(id);
        if(!content.getPassword().equals(password)) {
            return;
        }
        contentRepository.delete(id);
    }

    /**
     * 전체 글 조회
     */
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    /**
     * Id로 글 조회
     */
    public Content getContent(int id) {
        return contentRepository.findById(id);
    }

    /**
     * 글 개수 조회
     */
    public int getContentCount() {
        return contentRepository.findAll().size();
    }

    /**
     * 전체 글 삭제
     */
    public void deleteAllContent() {
        contentRepository.deleteAll();
    }

}
