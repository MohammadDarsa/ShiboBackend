package com.example.shibo.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailNotSentException extends RuntimeException{
    public EmailNotSentException(String msg) {
        super(msg);
    }
}
