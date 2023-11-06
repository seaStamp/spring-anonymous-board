package com.sparta.anonymousboard.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String author;
    private String password;
    private String content;
}
