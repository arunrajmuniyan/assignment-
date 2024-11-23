package com.core.assignment.admin.service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.core.assignment.admin.entity.UserEntity;
import com.core.assignment.admin.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtAuthenticationService {
	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public JwtAuthenticationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Method to authenticate user and generate JWT token
	public String authenticateUser(String username, String password) throws Exception {
		Optional<UserEntity> userOpt = userRepository.findByUsername(username);

		if (userOpt.isPresent()) {
			UserEntity user = userOpt.get();

			if (passwordEncoder.matches(password, user.getPassword())) {
				return generateToken(user.getUsername());
			} else {
				throw new Exception("Invalid password");
			}
		} else {
			throw new Exception("User not found");
		}
	}

	// Method to generate JWT token
	private String generateToken(String username) {
		long expirationTime = 1000 * 60 * 60; // 1 hour expiration

		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime)).signWith(SECRET_KEY).compact();
	}
}
