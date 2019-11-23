package com.javaboja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	
	@GetMapping("/regi")
	public String signUp() {
		return "signUp";
	}
	@GetMapping("/signin")
	public String main() {
		return "main";
	}
}
