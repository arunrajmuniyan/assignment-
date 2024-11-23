package com.core.assignment.admin.coreutil;

import java.io.IOException;
import java.security.Key;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	//private static final String SECRET_KEY = "admin"; // Replace with your secret key
	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		// Check if Authorization header contains Bearer token
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7); // Extract JWT token from the "Bearer" part

			try {
				// Validate the JWT token
				Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

				// Create authentication from claims (e.g., username) and set it in the
				// SecurityContext
				Authentication authentication = new JwtAuthentication(claims.getSubject());
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
				return;
			}
		}

		filterChain.doFilter(request, response); // Continue the filter chain
	}
}
