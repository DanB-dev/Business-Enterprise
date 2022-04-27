package uk.ac.bangor.csee.group3.spring.academigymraeg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Component
public class FirstUserConfigurer {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RepositoryBasedUserDetailsServiceImpl userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void createFirstUser() {
		try {
			// Ensures that if admin is already here it just updates to become an admin
			// everytime
			User loaded = (User) userDetailsService.loadUserByUsername("admin");
			if (!loaded.isAdmin()) {
				loaded.setUser(true);
				loaded.setAdmin(true);
				repository.save(loaded);
			}
		} catch (UsernameNotFoundException e) {
			// Creates user if not
			User firstUser = new User();
			firstUser.setUsername("admin");
			firstUser.setPassword(passwordEncoder.encode("admin"));
			firstUser.setAdmin(true);
			repository.save(firstUser);
		}
	}
}
