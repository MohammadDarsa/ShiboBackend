package com.example.shibo.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private String errorMessage, description;
    private Date dateAndTime;
}
