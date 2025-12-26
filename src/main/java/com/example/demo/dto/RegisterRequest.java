package com.example.demo.dto;
import com.example.demo.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username; // Added for Test 41
    private String email;
    private String password;
    private Role role;
}