package com.example.user_service.service;

import com.example.user_service.dto.UserDto;
import com.example.user_service.jpa.UserEntity;
import com.example.user_service.vo.ResponseUser;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
