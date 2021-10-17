package com.darsa.shibo.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
