package com.giri.springbootrestfulwebservices.mapper;

import com.giri.springbootrestfulwebservices.dto.UserDto;
import com.giri.springbootrestfulwebservices.entity.User;

public class UserMapper {

    // convert User JPA entity to User Dto
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    // convert user Dto to user JPA entity
    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }
}
