package com.javaboja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/login")
	public String signIn() {
		return "signIn";
	}
	@GetMapping("/regi")
	public String signUp() {
		return "signUp";
	}
	@GetMapping("/main")
	public String main() {
		return "main";
	}
}
