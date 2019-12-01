package com.javaboja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.javaboja.Entity.User;
import com.javaboja.repo.UserRepo;

@SpringBootApplication
public class RandomChatApplication {

	@Autowired
	private UserRepo userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(RandomChatApplication.class, args);
	}

	@Bean
	public void userAdd() {
		User user = new User();
		user.setUserId("a");
		user.setUserPassword("a");
		userRepo.save(user);
		
		User user2 = new User();
		user2.setUserId("b");
		user2.setUserPassword("b");
		userRepo.save(user2);
	}
	
}
