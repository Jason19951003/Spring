package com.example.demo.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2/github/login")
public class GitHubLogin {
	
	@GetMapping
	public String login() {
		// 建立 Github Oauth 2.0 授權URL
		String authUrl = OAuth2Util.AUTH_URL;
		return "redirect:" + authUrl;
	}
}
