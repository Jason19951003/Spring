package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mapper.UserMapper;

@SpringBootTest
class SpringSecurityApplicationTests {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	void contextLoads() {
		System.out.println(userMapper.findAllUsers());
	}

}
