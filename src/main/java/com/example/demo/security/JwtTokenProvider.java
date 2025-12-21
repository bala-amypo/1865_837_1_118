package com.example.demo.security;

import com.example.demo.entity.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
[cite_start]public class JwtTokenProvider { // [cite: 749]

    private final Key key;
    private final long validityInMs;

    public JwtTokenProvider(@Value("${app.jwt.secret}") String secret, 
                            @Value("${app.jwt.expiration-ms}") long validityInMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMs = validityInMs;
    }

    [cite_start]public String generateToken(AppUser user) { // [cite: 762]
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    [cite_start]public boolean validateToken(String token) { // [cite: 776]
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}