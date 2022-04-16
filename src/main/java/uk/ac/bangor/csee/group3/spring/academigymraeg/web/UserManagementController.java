package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Controller
public class UserManagementController {
	
	@Autowired
	private UserRepository repository;
	
	@RequestMapping("/users")
	public String showUserList(Model model) {
		List<User> listUsers = (List<User>) repository.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
		
}
