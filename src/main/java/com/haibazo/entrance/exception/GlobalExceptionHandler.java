package com.haibazo.entrance.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.haibazo.entrance.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ApiResponse<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ApiResponse.<String>builder().code(404).result("Failed!").message(ex.getMessage()).build();
    }

    public ApiResponse<String> handleResourceNotFormatException(ResourceNotFormatException ex) {
        return ApiResponse.<String>builder().code(406).result("Failed!").message(ex.getMessage()).build();
    }

    public ApiResponse<String> handleResourceExistedException(ResourceExistedException ex) {
        return ApiResponse.<String>builder().code(409).result("Failed!").message(ex.getMessage()).build();
    }
}
