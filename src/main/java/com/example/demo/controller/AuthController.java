package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.ApiResponse;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppUserRepository appUserRepository;

    public AuthController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        Optional<AppUser> existing = appUserRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Email already registered", null));
        }

        // For now, store raw password (no hashing since you said no security)
        AppUser user = new AppUser(request.getEmail(), request.getPassword(), request.getRole());
        AppUser saved = appUserRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", saved));
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        Optional<AppUser> userOpt = appUserRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid credentials", null));
        }

        AppUser user = userOpt.get();
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid credentials", null));
        }

        // Instead of JWT, just return a success message
        return ResponseEntity.ok(new ApiResponse(true, "Login successful", user));
    }
}