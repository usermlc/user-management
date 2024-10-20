package com.await.user_management_authentication_system.controller; // Package where the controller class is located

import com.await.user_management_authentication_system.model.User; // Importing the User model class
import com.await.user_management_authentication_system.repository.UserRepository; // Importing UserRepository to interact with the database
import org.springframework.security.core.annotation.AuthenticationPrincipal; // Importing annotation to access the authenticated user
import org.springframework.security.oauth2.core.user.OAuth2User; // Importing OAuth2User for handling OAuth2 user details
import org.springframework.stereotype.Controller; // Importing Controller annotation to mark this class as a Spring MVC controller
import org.springframework.web.bind.annotation.GetMapping; // Importing GetMapping to handle HTTP GET requests

@Controller // Annotation indicating this class is a Spring MVC controller
public class OAuth2Controller {

    private final UserRepository userRepository; // Declaring UserRepository to interact with the user database

    // Constructor to inject UserRepository dependency
    public OAuth2Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to handle OAuth2 callback after successful authentication
    @GetMapping("/oauth2/callback")
    public String oauth2Callback(@AuthenticationPrincipal OAuth2User principal) {
        // Extracting user information from the OAuth2 provider (e.g., Google)
        String username = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String oauth2Id = principal.getAttribute("sub"); // OAuth2 unique ID from the provider (e.g., Google)
        String provider = "google"; // Hardcoded to "google", can be dynamic for other providers

        // Check if the user already exists in the database by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            // If user doesn't exist, create a new user with OAuth2 details
            user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setOauth2Provider(provider); // Set the OAuth2 provider (e.g., Google)
            user.setOauth2Id(oauth2Id); // Set the user's OAuth2 ID
            userRepository.save(user); // Save the new user to the database
        }

        // Redirect the user to their profile after successful login or registration
        return "redirect:/profile";
    }

}
