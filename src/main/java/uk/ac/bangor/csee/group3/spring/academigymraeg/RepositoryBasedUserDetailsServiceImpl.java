package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Component
public class RepositoryBasedUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> u = repository.findById(username);
		
		if(u.isPresent())
			return u.get();
		throw new UsernameNotFoundException(username + "not found");
	}
	
	
	public boolean isUserAlreadyPresent(User user) {
		Optional<User> u = repository.findById(user.getUsername());
		if(u.isPresent())
			return true;
		else
			return false;
	}

}
