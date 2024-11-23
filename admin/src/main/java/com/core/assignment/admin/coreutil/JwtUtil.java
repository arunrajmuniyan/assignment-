package com.core.assignment.admin.coreutil;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	// Use the Keys utility to generate a secure key for HS256
	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	// Method to generate a token
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
				.signWith(key) // Use the generated secure key
				.compact();
	}

	// Method to validate the token
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key) // Use the same key to validate
					.build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
