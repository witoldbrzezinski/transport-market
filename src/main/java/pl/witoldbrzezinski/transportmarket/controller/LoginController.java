package pl.witoldbrzezinski.transportmarket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login() {
		
		return "login.html";
	}
	
	@RequestMapping("/register")
	public String register() {
		
		return "register.html";
	}
	

}
