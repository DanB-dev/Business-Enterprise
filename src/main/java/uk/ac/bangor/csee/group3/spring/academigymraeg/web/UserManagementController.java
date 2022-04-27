package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String deleteUser(@RequestParam String id) {
		repository.deleteById(id);

		return "redirect:/users";
	}

	@RequestMapping("/editUser")
	public String editUser(String id, Model model) {
		User user = (User) userDetailsService.loadUserById(id);
		model.addAttribute("user", user);

		return "editUser";
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, params = "action=update")
	public String updateUser(@ModelAttribute User user) {
		if (!user.getPassword().isEmpty()) {
			repository.updateDetailsWithPassword(user.getUsername(), user.isAdmin(), user.isPower(), user.isUser(),
					passwordEncoder.encode(user.getPassword()), user.getId());
		} else {
			repository.updateDetailsWithoutPassword(user.getUsername(), user.isAdmin(), user.isPower(), user.isUser(),
					user.getId());
		}
		return "redirect:/users";
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, params = "action=create")
	public String createUser(@ModelAttribute User user) {
		repository.save(user);
		return "redirect:/users";
	}

}
