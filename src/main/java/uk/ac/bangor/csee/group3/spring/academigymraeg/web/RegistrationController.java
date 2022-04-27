package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.bangor.csee.group3.spring.academigymraeg.RepositoryBasedUserDetailsServiceImpl;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Controller
@RequestMapping("/register")
@Secured("ROLE_ADMIN")
public class RegistrationController {
	// test pushing

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RepositoryBasedUserDetailsServiceImpl userDetailsService;

	@RequestMapping(method = RequestMethod.GET)
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegister(@ModelAttribute("user") @Valid User createUser, BindingResult result, Model model) {
		if (result.hasErrors())
			return showRegistrationPage(model);

		if (userDetailsService.isUserAlreadyPresent(createUser)) {
			model.addAttribute("createUser", new User());
			model.addAttribute("errorUsername", "Username already in use!");

			return showRegistrationPage(model);
		}

		createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));
		repository.save(createUser);

		return "redirect:/users";
	}

	@RequestMapping("/register_success")
	public String showRegisterSuccess() {
		return "register_success";
	}

}
