package com.app.auth_backend.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse<T> {

    private String message;
    private HttpStatus status;
    private T data;
    private Instant timestamp =  Instant.now();
    private boolean success;
}
