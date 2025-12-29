package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.ApiResponse;

public interface AuthService {
    ApiResponse register(RegisterRequest request);
    ApiResponse login(LoginRequest request);
}