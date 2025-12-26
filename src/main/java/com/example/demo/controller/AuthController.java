package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.exception.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager,
                          AppUserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    // POST /auth/register - Register new user [cite: 724]
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already in use");
        }

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        // Auto-login after register (optional, but returning token is standard per SRS "AuthResponse")
        String token = tokenProvider.generateToken(user);
        return ResponseEntity.ok(createAuthResponse(token));
    }

    // POST /auth/login - Login and obtain JWT token [cite: 729]
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // In a real scenario, you'd fetch the full user entity details after auth
        AppUser user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BadRequestException("User not found"));

        String token = tokenProvider.generateToken(user);
        return ResponseEntity.ok(createAuthResponse(token));
    }

    private Map<String, String> createAuthResponse(String token) {
        Map<String, String> response = new HashMap<>();
        response.put("accessToken", token);
        response.put("tokenType", "Bearer");
        return response;
    }
}