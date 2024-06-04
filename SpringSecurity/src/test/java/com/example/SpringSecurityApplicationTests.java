package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.mapper.UserRequestMapper;

@SpringBootTest
class SpringSecurityApplicationTests {
	
	@Autowired
	private UserRequestMapper userMapper;
	
	@Test
	void contextLoads() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("1234"));
		System.out.println(encoder.matches("1234", "$2a$10$HU787PvBZTMVxxmydmBmR.cDbMa.deBx6FNQ1cCJvLX4wlZqZrwdO"));
	}

}
