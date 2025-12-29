package com.example.demo.dto;

public class ApiResponse {
    private String message;
    private String token;

    // No-arg constructor
    public ApiResponse() {}

    // Constructor with parameters
    public ApiResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
