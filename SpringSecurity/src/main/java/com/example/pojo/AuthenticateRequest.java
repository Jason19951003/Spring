package com.example.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticateRequest {
	private String username;
	private String password;
}
