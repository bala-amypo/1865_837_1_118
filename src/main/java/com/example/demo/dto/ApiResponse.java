package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
[cite_start]public class ApiResponse { // [cite: 871]
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}