package com.app.auth_backend.services.impl;

import com.app.auth_backend.dto.UserDto;
import com.app.auth_backend.entities.User;
import com.app.auth_backend.exception.ResourceNotFoundException;
import com.app.auth_backend.repositories.UserRepository;
import com.app.auth_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws BadRequestException {
        if (userDto.getEmail() == null) {
            throw new BadRequestException("Email is required");
        }

        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        //save the user
        User newUser = modelMapper.map(userDto, User.class);
        userRepository.save(newUser);

        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws BadRequestException {
        if (userDto.getEmail() == null) {
            throw new BadRequestException("Email is required");
        }

        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        //save the user. Email we are not updating.
        User newUser = User.builder().
                id(userDto.getId()).
                image(userDto.getImage()).
                enable(userDto.isEnable()).
                password(userDto.getPassword()).
        build();

        userRepository.save(newUser);

        return userDto;
    }

    @Override
    public UserDto getUserById(UUID id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        return foundUser.map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(()  -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
