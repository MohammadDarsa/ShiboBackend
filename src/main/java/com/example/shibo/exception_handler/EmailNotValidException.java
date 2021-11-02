package com.example.shibo.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailNotValidException extends RuntimeException{
    public EmailNotValidException(String msg) {
        super(msg);
    }
}
