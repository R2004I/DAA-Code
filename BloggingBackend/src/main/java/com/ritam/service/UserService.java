package com.ritam.service;

import com.ritam.entity.User;
import com.ritam.payload.UserDto;

import java.util.List;

public interface UserService {



    User createUser(User user);
    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
