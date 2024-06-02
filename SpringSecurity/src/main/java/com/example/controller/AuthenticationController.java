package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.LoginUser;
import com.example.pojo.AuthenticateRequest;
import com.example.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsService userDetailsService;
	
	private final JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticateRequest request) {
		
		final LoginUser loginUser;
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getUsername(), request.getPassword()));
			loginUser = (LoginUser) userDetailsService.loadUserByUsername(request.getUsername());
			if (loginUser != null) {
				return ResponseEntity.ok(jwtUtil.generateToken(loginUser));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(e.getMessage());
		}
		return ResponseEntity.status(400).body("Some error occured");
	}
}
