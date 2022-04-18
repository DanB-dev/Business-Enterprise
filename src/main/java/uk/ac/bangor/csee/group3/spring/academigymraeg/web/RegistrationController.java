package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.ac.bangor.csee.group3.spring.academigymraeg.RepositoryBasedUserDetailsServiceImpl;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Controller
public class RegistrationController {
	//test pushing
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RepositoryBasedUserDetailsServiceImpl userDetailsService;
	
	@RequestMapping("/register")
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/process_register")
	public String processRegister(@Valid @ModelAttribute("user") User createUser, BindingResult result, Model model) {
		if(result.hasErrors())
			return "register";
		
		if(userDetailsService.isUserAlreadyPresent(createUser)) {
			model.addAttribute("createUser", new User());
			model.addAttribute("errorUsername","Username already in use!");
			
			return "register";
		}
			
		createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));
		repository.save(createUser);
		
		return "register_success";
	}
	
	@RequestMapping("/register_success")
	public String showRegisterSuccess() {
		return "register_success";
	}

}
