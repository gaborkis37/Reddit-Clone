package com.homeProj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.homeProj.service.UserService;

@Controller
public class AuthController {

	private UserService userService;

	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping("/profile")
	public String profile() {
		return "auth/profile";
	}

	@GetMapping("/register")
	public String register() {
		return "auth/register";
	}

}
