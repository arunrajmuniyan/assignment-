package com.core.assignment.admin.coreutil;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthentication extends AbstractAuthenticationToken {
	 private final String principal;

	    public JwtAuthentication(String principal) {
	        super(null); // No granted authorities
	        this.principal = principal;
	        setAuthenticated(true); // Mark as authenticated
	    }

	    @Override
	    public Object getCredentials() {
	        return null; // JWT doesn't contain credentials
	    }

	    @Override
	    public Object getPrincipal() {
	        return principal; // Return the user (e.g., username)
	    }
}
