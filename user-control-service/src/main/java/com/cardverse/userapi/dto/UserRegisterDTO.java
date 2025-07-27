package com.cardverse.userapi.dto;

public record UserRegisterDTO(
        String userName,
        String userEmail,
        String userMobile,
        String userPwd
){}