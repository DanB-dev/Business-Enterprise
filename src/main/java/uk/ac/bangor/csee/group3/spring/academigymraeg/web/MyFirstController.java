package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyFirstController {

	@RequestMapping("/welcome")
	public String welcome(Model model) {

		boolean loggedIn = false;
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		if (!userName.equals("anonymousUser")) {
			loggedIn = true;
		}

		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("username",userName);
		model.addAttribute("date", new Date());
		return "welcome";
	}

}
