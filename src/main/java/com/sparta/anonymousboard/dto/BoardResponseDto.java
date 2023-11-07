package com.sparta.anonymousboard.dto;

import com.sparta.anonymousboard.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto{
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.author = board.getAuthor();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
    }

}
