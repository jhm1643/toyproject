package com.javaboja.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaboja.Entity.Message;

public interface MessageRepo extends JpaRepository<Message, Long>{

	public List<Message> findByChatId(String chatId);
}
