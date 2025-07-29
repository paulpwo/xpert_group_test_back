package com.xpertgroup.demo.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.xpertgroup.demo.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.secret:mySecretKeyForJWTTokenGenerationAndValidationInDevelopmentEnvironment123456789}")
    private String secret;

    @Value("${jwt.expiration:7200}")
    private Long expiration;

    private SecretKey getSigningKey() {
        try {
            // Asegurar que la clave tenga al menos 256 bits (32 bytes)
            byte[] keyBytes = secret.getBytes();
            if (keyBytes.length < 32) {
                // Extender la clave si es muy corta
                byte[] extendedKey = new byte[32];
                System.arraycopy(keyBytes, 0, extendedKey, 0, Math.min(keyBytes.length, 32));
                return Keys.hmacShaKeyFor(extendedKey);
            }
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            log.error("Error creating signing key: {}", e.getMessage());
            throw new RuntimeException("Error creating JWT signing key", e);
        }
    }

    public String generateToken(User user) {
        try {
            log.debug("Generating JWT token for user: {}", user.getEmail());
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", user.getEmail());
            claims.put("userId", user.getId());
            
            String token = createToken(claims, user.getEmail());
            log.debug("JWT token generated successfully for user: {}", user.getEmail());
            return token;
        } catch (Exception e) {
            log.error("Error generating JWT token for user {}: {}", user.getEmail(), e.getMessage());
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (expiration * 1000));
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean validateToken(String token, String email) {
        final String tokenEmail = extractEmail(token);
        return (email.equals(tokenEmail) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Long getExpirationTime() {
        return expiration;
    }
}