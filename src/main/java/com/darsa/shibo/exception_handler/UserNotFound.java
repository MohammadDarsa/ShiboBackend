package com.darsa.shibo.exception_handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException{
    public UserNotFound(String msg) {
        super(msg);
    }
}
