package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, AppUserRepository userRepository,
                          PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // In a real app, we would get the UserDetails to create the token
        // For this example, we fetch the user entity again or cast principal
        String jwt = tokenProvider.generateToken(userRepository.findByEmail(loginRequest.getUsername()).orElseThrow());
        
        return ResponseEntity.ok(new ApiResponse(true, "Login success", jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email is already taken!", null), HttpStatus.BAD_REQUEST);
        }

        // Create user
        AppUser user = new AppUser(signUpRequest.getEmail(), 
                                   passwordEncoder.encode(signUpRequest.getPassword()), 
                                   signUpRequest.getRole());

        userRepository.save(user);

        return new ResponseEntity<>(new ApiResponse(true, "User registered successfully", null), HttpStatus.CREATED);
    }
}