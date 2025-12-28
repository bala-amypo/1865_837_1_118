package com.example.demo.dto;
import lombok.Data;
@Data
public class LoginRequest {
    private String username; // Note: Test file uses 'username' but logic maps to email often. Keeping strict to Test usage.
    private String password;
}