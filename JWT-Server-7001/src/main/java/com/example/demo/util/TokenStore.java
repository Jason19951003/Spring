package com.example.demo.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class TokenStore {
	
	private static Map<String, String> tokens = new ConcurrentHashMap<>();
	
	public String findTokenByService(String serverceId) {
		return tokens.get(serverceId);
	}
	
	public void storeToken(String serviceId, String token) {
		tokens.put(serviceId, token);
	}
}
