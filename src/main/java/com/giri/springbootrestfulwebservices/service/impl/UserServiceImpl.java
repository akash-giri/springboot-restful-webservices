package com.giri.springbootrestfulwebservices.service.impl;

import com.giri.springbootrestfulwebservices.dto.UserDto;
import com.giri.springbootrestfulwebservices.entity.User;
import com.giri.springbootrestfulwebservices.exception.EmailAlreadyExsitingException;
import com.giri.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.giri.springbootrestfulwebservices.repository.UserRepository;
import com.giri.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // convert userDto into User JPA entity
//        User user= UserMapper.mapToUser(userDto);
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if (byEmail.isPresent()){
            throw new EmailAlreadyExsitingException("Email Already exists for User");
        }
        User user= modelMapper.map(userDto,User.class);

        User savedUser = userRepository.save(user);
        // convert user JPA entity to UserDto
//        UserMapper.mapToUserDto(savedUser);
        return  modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",userId)
        );
//        User user1 = user.orElse(null);
//        assert user1 != null;
//        return UserMapper.mapToUserDto(user1);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
//        return userList.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());
        return userList.stream().map((user)->modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                ()->new ResourceNotFoundException("User","id",userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User savedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }

}
