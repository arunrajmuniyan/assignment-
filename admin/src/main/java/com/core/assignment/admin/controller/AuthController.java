package com.core.assignment.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.assignment.admin.requestdto.AuthenticationRequestDto;
import com.core.assignment.admin.service.JwtAuthenticationService;

@RestController
public class AuthController {
	 private final JwtAuthenticationService jwtAuthenticationService;

	    public AuthController(JwtAuthenticationService jwtAuthenticationService) {
	        this.jwtAuthenticationService = jwtAuthenticationService;
	    }

	    @PostMapping("/authenticate")
	    public String authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
	        try {
	            return jwtAuthenticationService.authenticateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());
	        } catch (Exception e) {
	            throw new RuntimeException("Authentication failed: " + e.getMessage());
	        }
	    }
}
