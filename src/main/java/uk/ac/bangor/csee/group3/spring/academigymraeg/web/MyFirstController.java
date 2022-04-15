package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.util.Date;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyFirstController {
	
	/**
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("date", new Date());
		return "welcome";
	}
	
	@RequestMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("/secure")
	public String secure() {
		return "secure";
	}
	**/
}
