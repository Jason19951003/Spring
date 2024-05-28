package com.example.demo.filter;

import java.io.IOException;

import com.example.demo.WebKeyUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/basic_auth/*")
public class BasicAuthFilter implements Filter {
	
	final String REALM = "MY_REPORT";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String authHeader = httpServletRequest.getHeader("Authorization");
		
		if (authHeader != null && WebKeyUtil.isValidBasicAuth(authHeader)) {
			// 驗證通過放行
			chain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			httpServletResponse.setHeader("WWW-Authenticate", WebKeyUtil.generateBasicChallenge(REALM));
			httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}
	}

}

