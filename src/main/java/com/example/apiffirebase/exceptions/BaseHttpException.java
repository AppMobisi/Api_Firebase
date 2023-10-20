package com.example.apiffirebase.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseHttpException extends Exception{
    private Integer statusCode;
    private String message;
}
