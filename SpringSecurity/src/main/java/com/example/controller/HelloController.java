package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("<h1>Hello From Our API</h1>");
	}
	
	@RequestMapping("/dog")
	public String dog() {
		return "<h1>狗在汪汪叫</h1>";
	}
}
