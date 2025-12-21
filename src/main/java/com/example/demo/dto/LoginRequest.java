package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest { // [cite: 843]
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}