package com.cardverse.userapi.controller;

import com.cardverse.userapi.dto.LoginRequestDTO;
import com.cardverse.userapi.dto.LoginResponseDTO;
import com.cardverse.userapi.dto.UserRegisterDTO;
import com.cardverse.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO dto){
        userService.registerUser(dto);
        return ResponseEntity.ok("User registered!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO dto){
        String token= userService.login(dto);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
