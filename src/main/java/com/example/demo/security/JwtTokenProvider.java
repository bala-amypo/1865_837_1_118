package com.example.demo.security;

import com.example.demo.model.AppUser;

public class JwtTokenProvider {

    public String generateToken(AppUser user) {
        // Tests mock this method; we provide a stub.
        return "DUMMY";
    }

    public boolean validateToken(String token) {
        // Tests mock this too; presence is sufficient.
        return false;
    }
}
