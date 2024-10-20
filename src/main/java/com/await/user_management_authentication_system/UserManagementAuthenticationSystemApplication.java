package com.await.user_management_authentication_system; // Package where the main application class is located

import org.springframework.boot.SpringApplication; // Importing SpringApplication class to launch the application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importing annotation to enable auto-configuration and component scanning in Spring Boot

@SpringBootApplication // Marks this class as a Spring Boot application, enabling auto-configuration and component scanning
public class UserManagementAuthenticationSystemApplication {

	public static void main(String[] args) {
		// Launches the Spring Boot application
		SpringApplication.run(UserManagementAuthenticationSystemApplication.class, args);
	}

}
