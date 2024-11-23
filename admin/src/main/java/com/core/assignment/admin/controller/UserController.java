package com.core.assignment.admin.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.assignment.admin.entity.UserEntity;
import com.core.assignment.admin.repository.UserRepository;

@RestController
//@RequestMapping("/protected")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/data")
	public ResponseEntity<String> getProtectedData() {
		// Get the authenticated user's username (from JWT claims)
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Return protected data
		return ResponseEntity.status(HttpStatus.OK.value()).body("Protected data accessed by user: " + username) ;
	}

	@GetMapping("/users")
	public ResponseEntity<Page<UserEntity>> getUsers(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<UserEntity> users = userRepository.findAll(pageable);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/users/download")
	public ResponseEntity<byte[]> downloadUsers(@RequestParam int page, @RequestParam int size) throws IOException {
		Pageable pageable = PageRequest.of(page, size);
		List<UserEntity> users = userRepository.findAll(pageable).getContent();

		// ByteArrayInputStream in = ExcelGenerator.generate(users);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=users.xlsx");

		return ResponseEntity.ok().headers(headers).body(null);
	}
}
