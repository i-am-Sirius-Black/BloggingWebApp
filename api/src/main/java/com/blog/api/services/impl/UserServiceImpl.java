package com.blog.api.services.impl;

import com.blog.api.config.AppConstants;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.UserDto;
import com.blog.api.services.UserService;
import com.blog.api.repositories.*;
import com.blog.api.entities.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private UserRepo userRepo;
   @Autowired
   private ModelMapper modelMapper;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);

        //Encoding the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //Roles
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);


        return this.modelMapper.map(newUser, UserDto.class);
    }


    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);

        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        // list all users
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
         User user=this.modelMapper.map(userDto,User.class);
         return user;

    }

    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
