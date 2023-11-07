package com.sparta.anonymousboard.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto extends RequestDto{
    private String title;
    private String author;
    private String content;
}
