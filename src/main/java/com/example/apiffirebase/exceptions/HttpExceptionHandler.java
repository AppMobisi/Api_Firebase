package com.example.apiffirebase.exceptions;

import org.springframework.http.HttpStatus;

public class HttpExceptionHandler {
    public static BaseHttpException handleException(Exception exc) {
        if (exc instanceof BaseHttpException) {
            return (BaseHttpException) exc;
        } else {
            return new BaseHttpException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something bad happened");
        }
    }
}
