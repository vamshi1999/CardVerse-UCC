package com.cardverse.userapi.service;

import com.cardverse.userapi.dto.LoginRequestDTO;
import com.cardverse.userapi.dto.UserRegisterDTO;
import com.cardverse.userapi.dto.UserResponseDTO;
import com.cardverse.userapi.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User registerUser(UserRegisterDTO userRegisterDTO);
    String login(LoginRequestDTO loginRequestDTO);
    UserResponseDTO getUserById(Long userId);
    List<UserResponseDTO> getAllUsers();
    User updateUser(Long userId, UserRegisterDTO userRegisterDTO);
    void deleteUser(Long userId);
}
