package com.example.demo.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * BasicAuthController 是一個實現了 HTTP Basic Authentication 的 Controller。
 * 由於用戶名和密碼在 HTTP 請求中以 Base64 編碼的形式發送（而非加密形式），
 * 注意：在實務應用上必須使用 HTTPS 進行通信，以確保傳輸過程中的安全性。
 * 
 * 預設的用戶名和密碼，用於HTTP Basic和Digest認證
 * username = user
 * password = 1234
 * */

@RestController
@RequestMapping("/basic_auth")
public class BasicAuthController {
	
	// 有一個報告連結, 該報告連結需要透過 BasicAuth 才可以觀看
	@GetMapping("/report1")
	public String report1(@RequestHeader(value = "Authorization", required = false) String authHeader) {
		return "Protected Report 1!";
		/*if (authHeader != null && WebKeyUtil.isValidBasicAuth(authHeader)) {
			// 驗證成功, 給予受保護的資源
			return ResponseEntity.ok("Protected Report !");
		}
		
		// 驗證失敗/沒有驗證資料, 返回 401 未授權的回應給瀏覽器
		HttpHeaders headers = new HttpHeaders();
		final String REALM = "MY_REPORT";
		headers.set("WWW-Authenticate", WebKeyUtil.generateBasicChallenge(REALM));
		return new ResponseEntity<String>("Unauthorized", headers, HttpStatus.UNAUTHORIZED);*/
	}
	
	@GetMapping("/report2")
	public String report2() {
		return "Protected Report 2!";
	}
	
	@GetMapping("/report3")
	public String report3() {
		return "Protected Report 2!";
	}
}






