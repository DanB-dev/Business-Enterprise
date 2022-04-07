package uk.ac.bangor.csee.group3.spring.academigymraeg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.UserRepository;

@Configuration
public class AcademiGymraegSecurityConfigurator extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RepositoryBasedUserDetailsServiceImpl userDetailsService;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().antMatchers("/welcome").permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/secure").and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).and()
		.userDetailsService(userDetailsService);
	}
	
	private PasswordEncoder createPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@PostConstruct
	public void createFirstUser() {
		try {
			userDetailsService.loadUserByUsername("admin");
			
		}catch (UsernameNotFoundException e){
			User firstUser = new User();
			firstUser.setUsername("admin");
			firstUser.setPassword(createPasswordEncoder().encode("admin"));
			repository.save(firstUser);
		}
	}
	
}
