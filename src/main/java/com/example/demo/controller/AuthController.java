package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            AppUserRepository appUserRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider) {
        this.appUserRepository = appUserRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {

        if (appUserRepository.existsByUsername(req.getUsername())) {
            throw new BadRequestException("Username already taken");
        }

        if (appUserRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email already taken");
        }

        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());

        AppUser saved = appUserRepository.save(user);
        return jwtTokenProvider.generateToken(saved);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        AppUser user = appUserRepository.findByEmail(req.getUsername())
                .orElseThrow(() ->
                        new BadRequestException("Invalid credentials"));

        return jwtTokenProvider.generateToken(user);
    }
}
