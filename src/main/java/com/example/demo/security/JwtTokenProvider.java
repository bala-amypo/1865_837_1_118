// // package com.example.demo.security;

// // import com.example.demo.model.AppUser;
// // import org.springframework.stereotype.Component;

// // @Component
// // public class JwtTokenProvider {
    
// //     public String generateToken(AppUser user) {
// //         return "JWT_TOKEN_" + user.getUsername();
// //     }
    
// //     public boolean validateToken(String token) {
// //         return token != null && token.startsWith("JWT_TOKEN_");
// //     }

// //     // ✅ Add this method to extract username from token
// //     public String getUsernameFromToken(String token) {
// //         if (validateToken(token)) {
// //             return token.substring("JWT_TOKEN_".length());
// //         }
// //         return null;
// //     }
// // }

// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// @Component
// public class JwtTokenProvider {

//     // ✅ REAL Base64 256-bit key
//     private static final String SECRET_KEY =
//             "yW1n1zN3y8kHqAqYlZQzZ4M6m9Q2B8kHnR7L2wYpJX8=";

//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }

//     public boolean isTokenValid(String token, UserDetails userDetails) {
//         final String username = extractUsername(token);
//         return username.equals(userDetails.getUsername())
//                 && !isTokenExpired(token);
//     }

//     public String generateToken(UserDetails userDetails) {
//         return generateToken(new HashMap<>(), userDetails);
//     }

//     public String generateToken(
//             Map<String, Object> extraClaims,
//             UserDetails userDetails) {

//         return Jwts.builder()
//                 .setClaims(extraClaims)
//                 .setSubject(userDetails.getUsername())
//                 .setIssuedAt(new Date())
//                 // ✅ 24 HOURS
//                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
//                 .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     private boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }

//     private Date extractExpiration(String token) {
//         return extractClaim(token, Claims::getExpiration);
//     }

//     public <T> T extractClaim(
//             String token,
//             Function<Claims, T> claimsResolver) {

//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }

//     private Claims extractAllClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSignInKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     private Key getSignInKey() {
//         byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//         return Keys.hmacShaKeyFor(keyBytes);
//     }
// }
package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "your_very_secret_key_that_is_long_enough_for_sha256";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ REQUIRED BY YOUR TESTS
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ REQUIRED BY YOUR FILTER
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    // ✅ REQUIRED BY YOUR TESTS
    public String getUsernameFromToken(String token) {
        return extractUsername(token);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }
}