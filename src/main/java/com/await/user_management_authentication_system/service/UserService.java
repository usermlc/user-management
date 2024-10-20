package com.await.user_management_authentication_system.service; // Package where the service class is located

import com.await.user_management_authentication_system.dto.UserRegistrationDto; // Importing UserRegistrationDto for handling user registration data transfer
import com.await.user_management_authentication_system.model.User; // Importing the User model class
import com.await.user_management_authentication_system.repository.UserRepository; // Importing UserRepository to interact with the User database
import org.springframework.security.crypto.password.PasswordEncoder; // Importing PasswordEncoder to securely hash passwords
import org.springframework.stereotype.Service; // Importing Service annotation to mark this as a service component in Spring

@Service // Annotation indicating that this class is a Spring-managed service
public class UserService {

    private final UserRepository userRepository; // Declaring UserRepository to interact with the database

    private final PasswordEncoder passwordEncoder; // Declaring PasswordEncoder to handle password encryption

    // Constructor for injecting UserRepository and PasswordEncoder dependencies
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new user
    public void registerUser(UserRegistrationDto userDto) {
        User user = new User(); // Create a new User entity
        user.setUsername(userDto.getUsername()); // Set the username from the DTO
        user.setEmail(userDto.getEmail()); // Set the email from the DTO
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encode the password and set it
        userRepository.save(user); // Save the user to the database
    }
}
