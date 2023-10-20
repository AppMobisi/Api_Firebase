package com.example.apiffirebase.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse implements ApiResponse{
    public Integer statusCode;
    public String message;
}
