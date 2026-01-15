package com.app.auth_backend.exception;

import com.app.auth_backend.helper.AuthResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    ResponseEntity<AuthResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        AuthResponse<?> authResponse = AuthResponse.builder().success(false).message(ex.getMessage()).build();
        return new ResponseEntity<>(authResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    ResponseEntity<AuthResponse<?>> badRequestExceptionHandler(BadRequestException ex) {
        AuthResponse<?> authResponse = AuthResponse.builder().success(false).message(ex.getMessage()).build();
        return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    ResponseEntity<AuthResponse<?>> runtimeExceptionHandler(RuntimeException ex) {
        AuthResponse<?> authResponse = AuthResponse.builder().success(false).message(ex.getMessage()).build();
        return new ResponseEntity<>(authResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity<AuthResponse<?>> exceptionHandler(Exception ex) {
        AuthResponse<?> authResponse = AuthResponse.builder().success(false).message(ex.getMessage()).build();
        return  new ResponseEntity<>(authResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
