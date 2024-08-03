package dev.dwidi.transactionservice.entity.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
}
