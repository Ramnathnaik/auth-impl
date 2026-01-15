package com.app.auth_backend.services;

import com.app.auth_backend.dto.UserDto;
import org.apache.coyote.BadRequestException;

import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto userDto) throws BadRequestException;

    UserDto updateUser(UserDto userDto) throws BadRequestException;

    UserDto getUserById(UUID id);

    UserDto getUserByEmail(String email);

    void deleteUser(UUID id);

}
