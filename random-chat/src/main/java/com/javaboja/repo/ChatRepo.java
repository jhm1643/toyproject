package com.javaboja.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaboja.Entity.Chat;

public interface ChatRepo extends JpaRepository<Chat, Long>{

	public List<Chat> findByUserId1IsNullAndUserId2IsNull();
	public List<Chat> findByUserId2IsNull();
}
