package com.radix.ems.exceptions;

import com.radix.ems.utilities.ApiResponse;
import com.radix.ems.utilities.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(HttpServletRequest request, Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiResponse<Void> response = ResponseUtil.error(errors, "An error occurred", 1000, request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex) {
        ApiResponse<Void> response = ResponseUtil.error(Collections.singletonList(ex.getMessage()), "Resource not found", 1001, request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceAlreadyExistException(HttpServletRequest request, ResourceAlreadyExistException ex) {
        ApiResponse<Void> response = ResponseUtil.error(ex.getErrors(), "Resource already exists", 1002, request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

