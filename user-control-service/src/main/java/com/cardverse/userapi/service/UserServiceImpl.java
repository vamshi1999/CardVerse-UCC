package com.cardverse.userapi.service;

import com.cardverse.userapi.config.JwtUtil;
import com.cardverse.userapi.dto.LoginRequestDTO;
import com.cardverse.userapi.dto.UserRegisterDTO;
import com.cardverse.userapi.dto.UserResponseDTO;
import com.cardverse.userapi.exception.CvUserException;
import com.cardverse.userapi.model.User;
import com.cardverse.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegisterDTO userRegisterDTO) {
        if(userRepository.existsByUserEmail(userRegisterDTO.userEmail()))
            throw new CvUserException("User already exists!");

        User user=new User();
        user.setUserName(userRegisterDTO.userName());
        user.setUserEmail(userRegisterDTO.userEmail());
        user.setUserPwdHash(passwordEncoder.encode(userRegisterDTO.userPwd()));
        user.setCreated_at(LocalDateTime.now());
        user.setModified_at(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        User user=userRepository.findByUserEmail(loginRequestDTO.userEmail()).orElseThrow(() -> new CvUserException("Invalid Credentials: Email!"));

        if(!passwordEncoder.matches(loginRequestDTO.userPwd(),user.getUserPwdHash()))
            throw new CvUserException("Invalid Credentials: Password!");

        return jwtUtil.generateToken(user.getUserEmail());
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new CvUserException("User not found!"));
        return new UserResponseDTO(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserMobile());
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map((user)-> new UserResponseDTO(user.getUserId(),user.getUserName(),user.getUserEmail(),user.getUserMobile()))
                .collect(Collectors.toList());
    }

    @Override
    public User updateUser(Long userId, UserRegisterDTO userRegisterDTO) {
        User user=userRepository.findById(userId).orElseThrow(()->new CvUserException("User not found!"));
        user.setUserName(userRegisterDTO.userName());
        user.setUserMobile(user.getUserMobile());
        user.setModified_at(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
