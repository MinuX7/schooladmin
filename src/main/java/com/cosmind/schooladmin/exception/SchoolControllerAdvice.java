package com.cosmind.schooladmin.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class SchoolControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        ApiError apiError = new ApiError("Invalid database state after processing your request", List.of(dataIntegrityViolationException.getMessage()));
        return ResponseEntity.internalServerError().body(apiError);
    }
}
