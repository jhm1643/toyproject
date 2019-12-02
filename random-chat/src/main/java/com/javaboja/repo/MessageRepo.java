package com.javaboja.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaboja.Entity.Message;

public interface MessageRepo extends JpaRepository<Message, Long>{


}
