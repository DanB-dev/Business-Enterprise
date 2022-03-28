package uk.ac.bangor.csee.group3.spring.academigymraeg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AcademiGymraegApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiGymraegApplication.class, args);
	}

}
