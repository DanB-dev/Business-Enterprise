package uk.ac.bangor.csee.group3.spring.academigymraeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class AcademiGymraegSecurityConfigurator extends WebSecurityConfigurerAdapter {

	@Autowired
	private RepositoryBasedUserDetailsServiceImpl userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/secure").authenticated().anyRequest().permitAll().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/secure").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).and()
				.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.inMemoryAuthentication().withUser("spring").password(passwordEncoder().encode("secret")).roles("USER");

	}

}
