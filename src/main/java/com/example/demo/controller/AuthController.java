// package com.example.demo.controller;

// import com.example.demo.dto.*;
// import com.example.demo.exception.BadRequestException;
// import com.example.demo.model.AppUser;
// import com.example.demo.repository.AppUserRepository;
// import com.example.demo.security.JwtTokenProvider;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// @RequiredArgsConstructor
// public class AuthController {
//     private final AuthenticationManager authenticationManager;
//     private final AppUserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtTokenProvider tokenProvider;

//     @PostMapping("/register")
//     public ApiResponse register(@RequestBody RegisterRequest req) {
//         if(userRepository.existsByUsername(req.getUsername())) {
//             throw new BadRequestException("Username already taken");
//         }
//         AppUser user = new AppUser();
//         user.setUsername(req.getUsername());
//         user.setEmail(req.getEmail());
//         user.setPassword(passwordEncoder.encode(req.getPassword()));
//         user.setRole(req.getRole());
//         userRepository.save(user);
//         return new ApiResponse(true, "User registered", null);
//     }

//     @PostMapping("/login")
//     public Map<String, String> login(@RequestBody LoginRequest req) {
//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        
//         AppUser user = userRepository.findByUsername(req.getUsername())
//                 .orElseThrow(() -> new RuntimeException("User not found"));
        
//         String token = tokenProvider.generateToken(user);
//         Map<String, String> res = new HashMap<>();
//         res.put("token", token);
//         return res;
//     }
// }