package io.getarrays.UserService;

import io.getarrays.UserService.entities.AppRole;
import io.getarrays.UserService.entities.AppUser;
import io.getarrays.UserService.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	//o'qish garak, nachun BCr... Passw... ni yuvarib biladi
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new AppRole(null, "ROLE_USER"));
//			userService.saveRole(new AppRole(null, "ROLE_MANAGER"));
//			userService.saveRole(new AppRole(null, "ROLE_ADMIN"));
//			userService.saveRole(new AppRole(null, "ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new AppUser(null, "John Travolta", "john", "1234", null, null));
//			userService.saveUser(new AppUser(null, "Will Smith", "will", "11", null, null));
//			userService.saveUser(new AppUser(null, "Jim Carry", "jim", "22", null, null));
//			userService.saveUser(new AppUser(null, "Arnold Schwarzenegger", "arnold", "33", null, null));
//
//			userService.addRoleToUser("john", "ROLE_USER");
//			userService.addRoleToUser("will", "ROLE_MANAGER");
//			userService.addRoleToUser("jim", "ROLE_ADMIN");
//			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
//		};
//	}
}
