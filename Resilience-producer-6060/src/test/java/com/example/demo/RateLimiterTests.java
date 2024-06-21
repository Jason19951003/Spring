package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RateLimiterTests {

	@Test
	void test() throws Exception {
		// 先發送10次正常請求
		int i = 1;
		for (; i<=10; i++) {
			System.out.printf("發送第 %d 次 => ", i);
			String responseMessage = getEmployee();
			System.out.println("Response Message: " + responseMessage);
		}
		
		// 第11次
		System.err.printf("發送第 %d 次 => ", i);
		String responseMessage = getEmployee();
		System.err.println("Response Message: " + responseMessage);
		
		System.out.println("停 1秒鐘");
		Thread.sleep(1000);
		
		// 第12次
		System.out.printf("發送第 %d 次 => ", ++i);
		responseMessage = getEmployee();
		System.out.println("Response Message: " + responseMessage);
	}
	
	private String getEmployee() throws Exception {
		URL url = new URL("http://172.20.10.11:6060/employee/ratelimiter/1");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {
			return br.lines().collect(Collectors.joining());
		}
	}
}
