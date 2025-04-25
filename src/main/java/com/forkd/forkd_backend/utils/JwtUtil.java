package com.forkd.forkd_backend.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds. you have to relogin after a day
    
    public String generateToken(String userId, String username, String role) {
    	return Jwts.builder()
    			.setSubject(username)
    			.claim("userId", userId)
    			.claim("role", role)
    			.setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
    
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
        	 System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
    
    public Long getUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String getRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
    
}
