package com.blog.api.services;

import com.blog.api.payloads.UserDto;
import org.springframework.stereotype.Service;


import java.util.List;


public interface UserService {

    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
