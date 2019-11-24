package com.javaboja.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaboja.Entity.User;
import com.javaboja.service.UserRegiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MainController {

	@Autowired
	private UserRegiService userRegiService;
	
	@PostMapping("/regi")
	public Map<String, Object> regi(@RequestParam String userId,
									@RequestParam String userPassword) {
		User user = new User();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		Map<String, Object> map = userRegiService.regiService(user);
		return map;
	}
	@PostMapping("/main")
	public Map<String, Object> main(@RequestParam String userId,
									@RequestParam String userPassword) {
		User user = new User();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		Map<String, Object> map = userRegiService.loginService(user);
		return map;
	}
}
