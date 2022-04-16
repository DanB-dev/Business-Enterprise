package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/register")
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		
		return "register_success";
	}
	
	@RequestMapping("/register_success")
	public String showRegisterSuccess() {
		return "register_success";
	}

}
