package com.example.demo.filter;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.WebKeyUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/form_auth/*")
public class LoginFilter implements Filter {

	static Map<String, Map<String, String>> users = new HashMap<>();
	
	static {
		// 密碼 1234
		users.put("user", Map.of("hash", "029196baa1fc5c280a50d011512c5f587ac5811e34ccc0eaa51916de67ab7163", "salt", "3c691b75a768d00cb87e82d6d20cb6cb"));
		// 密碼 5678
		users.put("john", Map.of("hash", "12da5dd44144ab27196cca82c98119d943ba7244994a661a0f893ce63c3e7e84", "salt", "9ef3e3ccd89ffe3c67e208d18551020c"));
		// 密碼 2234
		users.put("mary", Map.of("hash", "2999dc04047b5c2994bb369b0b1832689a7e76fd35780866fbf27fd5fa23a281", "salt", "2e91d26a6f1c017e02ddfb31801cc220"));
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession httpSession = req.getSession();
		Object loginStatue = httpSession.getAttribute("loginStatue");
		
		// 判斷使用者是否已登入
		if (loginStatue != null && Boolean.valueOf(loginStatue+"")) {
			chain.doFilter(req, res);
		} else {
			// 驗證 username & password
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			if (username == null || username.trim().length() == 0) {
				res.sendRedirect("/login");
				return;
			}
			// 是否有此user
			Map<String, String> user = users.get(username);
			if (user == null) {
				System.out.println("無此使用者");
				res.sendRedirect("/login");
				return;
			}
			String hash = user.get("hash");
			byte[] salt = WebKeyUtil.hexStringToByteArray(user.get("salt"));
			try {
				MessageDigest msDigest = MessageDigest.getInstance("SHA-256");
				msDigest.reset(); // 重製
				msDigest.update(salt);
				
				byte[] inputHashedBytes = msDigest.digest(password.getBytes());
				// 根據使用者輸入的 password 與已知的salt 來產出 comparedHash
				// 比較comparedHash 和 hash 是否相同
				String comparedHash = WebKeyUtil.bytesToHexString(inputHashedBytes);
				
				if (hash.equals(comparedHash)) {
					// 儲存登入狀態
					httpSession.setAttribute("loginStatue", true);
					// 放行
					chain.doFilter(req, res);
					return;
				} else {
					System.out.println("登入失敗");
					res.sendRedirect("/login");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res.sendRedirect("/login");
			}
		}
	}

}
