package com.app.auth_backend.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
