package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class TestController {
	
	@RequestMapping(
		value = {"/test", "/request"},
		method = {RequestMethod.GET, RequestMethod.POST}
	)
	public String success() {
		return "success";
	}
	
	@GetMapping("/testGetMapping")
	public String testGetMapping() {
		return "success";
	}
	
	@GetMapping(
		value = {"/testParam"},
		params = {"userName"}
	)
	public String getParam() {
		return "success";
	}
	
	@PostMapping(
		value = {"/testParam"},
		params = {"userName"}
	)
	public String postMethodName(HttpServletRequest req) {
		System.out.println(req.getParameter("userName"));
		return "success";
	}
	
}
