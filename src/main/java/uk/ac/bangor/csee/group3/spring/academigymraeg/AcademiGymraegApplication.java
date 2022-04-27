package uk.ac.bangor.csee.group3.spring.academigymraeg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AcademiGymraegApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiGymraegApplication.class, args);
		// Test push
	}

}
