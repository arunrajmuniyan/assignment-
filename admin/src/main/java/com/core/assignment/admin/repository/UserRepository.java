package com.core.assignment.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.assignment.admin.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	 Optional<UserEntity> findByUsername(String username);
}
