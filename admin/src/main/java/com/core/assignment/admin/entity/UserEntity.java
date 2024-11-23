package com.core.assignment.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(MyEntityListener.class)
@Table(name = "users")
public class UserEntity {
	@Id
	private Long id;
	private String username;
	private String password; // Store hashed password

	// Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
