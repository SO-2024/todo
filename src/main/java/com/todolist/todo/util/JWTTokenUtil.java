package com.todolist.todo.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import com.todolist.todo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Utility class for handling JWT tokens.
 */
@Component
public class JWTTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours
    private static final String secret = "SecretKey";

    /**
     * Extract the email from the JWT token.
     * 
     * @param token The JWT token.
     * @return The email extracted from the token.
     */
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Get the expiration date from the JWT token.
     * 
     * @param token The JWT token.
     * @return The expiration date of the token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Extract a specific claim from the JWT token.
     * 
     * @param token The JWT token.
     * @param claimsResolver A function to extract the claim.
     * @param <T> The type of the claim.
     * @return The claim value.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Get all claims from the JWT token.
     * 
     * @param token The JWT token.
     * @return The claims from the token.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
    }

    /**
     * Check if the JWT token is expired.
     * 
     * @param token The JWT token.
     * @return True if the token is expired, otherwise false.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generate a new JWT token based on user details.
     * 
     * @param userDetails The user details for generating the token.
     * @return The generated JWT token.
     */
    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getEmail());
    }

    /**
     * Create a JWT token with specified claims and subject.
     * 
     * @param claims The claims to include in the token.
     * @param subject The subject for the token (usually the email).
     * @return The generated JWT token.
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                   .signWith(SignatureAlgorithm.HS512, secret)
                   .compact();
    }

    /**
     * Validate the JWT token.
     * 
     * @param token The JWT token.
     * @param userDetails The user details to validate against.
     * @return True if the token is valid, otherwise false.
     */
    public Boolean validateToken(String token, User userDetails) {
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }
    
    /**
     * Validate the JWT token without user details.
     * 
     * @param token The JWT token.
     * @return True if the token is valid, otherwise false.
     */
    public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}
}
