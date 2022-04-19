package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.ac.bangor.csee.group3.spring.academigymraeg.RepositoryBasedUserDetailsServiceImpl;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Controller
@Secured("ROLE_ADMIN")
public class UserManagementController {
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RepositoryBasedUserDetailsServiceImpl userDetailsService;
	
	@RequestMapping("/users")
	public String showUserList(Model model) {
		List<User> listUsers = (List<User>) repository.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@PostMapping("/deleteUser")
	public String deleteUser(@RequestParam String user) {
		repository.deleteById(user);
		
		return "redirect:/users";
	}
	
	@RequestMapping("/editUser")
	public String editUser(String username, Model model) {
		User user = (User) userDetailsService.loadUserByUsername(username);
		model.addAttribute("user", user);
		
		
		return "editUser";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		
		return "redirect:/users";
	}
		
}
