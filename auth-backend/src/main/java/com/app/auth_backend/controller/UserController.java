package com.app.auth_backend.controller;

import com.app.auth_backend.dto.UserDto;
import com.app.auth_backend.entities.User;
import com.app.auth_backend.exception.ResourceNotFoundException;
import com.app.auth_backend.helper.AuthResponse;
import com.app.auth_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<AuthResponse<?>> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.createUser(userDto);
            AuthResponse<Object> authResponse = AuthResponse.builder()
                    .data(user)
                    .success(true)
                    .build();
            return ResponseEntity.ok(authResponse);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/email/{email}")
    ResponseEntity<AuthResponse<?>> getUser(@PathVariable String email) {
        try {
           UserDto user = userService.getUserByEmail(email);
           AuthResponse<Object> authResponse = AuthResponse.builder()
                   .data(user)
                   .success(true)
                   .build();
           return ResponseEntity.ok(authResponse);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @GetMapping("/id/{id}")
    ResponseEntity<AuthResponse<?>> getUserById(@PathVariable String id) {
        try {
            UserDto user = userService.getUserById(UUID.fromString(id));
            AuthResponse<Object> authResponse = AuthResponse.builder()
                    .data(user)
                    .success(true)
                    .build();
            return ResponseEntity.ok(authResponse);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PutMapping
    ResponseEntity<AuthResponse<?>> updateUser(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.updateUser(userDto);
            AuthResponse<Object> authResponse = AuthResponse.builder()
                    .data(user)
                    .success(true)
                    .build();
            return ResponseEntity.ok(authResponse);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/id/{id}")
    ResponseEntity<AuthResponse<?>> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(UUID.fromString(id));
            AuthResponse<Object> authResponse = AuthResponse.builder()
                    .success(true)
                    .message("User deleted successfully")
                    .build();
            return ResponseEntity.ok(authResponse);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

}
