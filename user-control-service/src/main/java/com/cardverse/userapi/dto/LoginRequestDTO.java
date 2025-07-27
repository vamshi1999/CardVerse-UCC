package com.cardverse.userapi.dto;

public record LoginRequestDTO(
        String userEmail,
        String userPwd
) {
}
