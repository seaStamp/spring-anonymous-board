package com.sparta.anonymousboard.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BoardException extends RuntimeException{
    private HttpStatus status;
    public BoardException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.status = errorMessage.getStatus();
    }
}
