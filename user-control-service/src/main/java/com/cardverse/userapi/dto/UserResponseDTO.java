package com.cardverse.userapi.dto;

public record UserResponseDTO(
        Long userId,
        String userName,
        String userEmail,
        String userMobile
) {
}
