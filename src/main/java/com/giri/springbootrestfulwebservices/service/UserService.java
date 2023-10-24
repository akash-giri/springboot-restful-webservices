package com.giri.springbootrestfulwebservices.service;

import com.giri.springbootrestfulwebservices.dto.UserDto;
import com.giri.springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long userId);
}
