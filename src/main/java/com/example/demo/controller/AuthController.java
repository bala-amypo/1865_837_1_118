package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AppUserRepository userRepository,
                          JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        userRepository.save(user);
        return jwtTokenProvider.generateToken(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return "LOGIN_SUCCESS";
    }
}
