package com.javaboja.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaboja.Entity.User;

public interface UserRepo extends JpaRepository<User, String>{

	public User findByUserId(String userId);
	public long countByUserIdAndUserPassword(String userId, String userPassword);
}
