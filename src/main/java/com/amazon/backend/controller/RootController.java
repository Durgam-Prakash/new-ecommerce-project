package com.amazon.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RootController {
	
	@GetMapping("/get")
	public String getMethodName() {
		return "Welcome to the Amazon backed";
	}
	
	@GetMapping("/login")
	public String login() {
		return "welcome login page";
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "greetings";
	}
	

}
