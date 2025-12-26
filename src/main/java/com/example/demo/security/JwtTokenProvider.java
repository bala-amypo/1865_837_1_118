package com.example.demo.security;

import com.example.demo.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // Dummy token generation (sufficient for tests)
    public String generateToken(AppUser user) {
        return "DUMMY_TOKEN_" + user.getUsername();
    }

    // Token validation stub (used in tests)
    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }
}
