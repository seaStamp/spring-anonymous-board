package com.sparta.anonymousboard.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseDto {
    private int code;
    private HttpStatus status;
    private String error;

    public ErrorResponseDto(HttpStatus status, String error) {
        this.code = status.value();
        this.status = status;
        this.error = error;
    }
}
