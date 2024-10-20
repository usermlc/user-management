package com.await.user_management_authentication_system.controller; // Package where the controller class is located

import org.springframework.security.core.annotation.AuthenticationPrincipal; // Importing annotation to access the authenticated user's details
import org.springframework.security.core.userdetails.UserDetails; // Importing UserDetails to handle authenticated user details for regular login
import org.springframework.security.oauth2.core.user.OAuth2User; // Importing OAuth2User to handle OAuth2 user details
import org.springframework.stereotype.Controller; // Importing Controller annotation to mark this class as a Spring MVC controller
import org.springframework.ui.Model; // Importing Model to pass data to the view
import org.springframework.web.bind.annotation.GetMapping; // Importing GetMapping to handle HTTP GET requests

@Controller // Annotation indicating this class is a Spring MVC controller
public class ProfileController {

    // Method to handle the profile view, showing authenticated user information
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal Object principal, Model model) {
        String username = null;
        String email = null;

        // Check if the authenticated user is an OAuth2 user
        if (principal instanceof OAuth2User oauthUser) {
            username = oauthUser.getAttribute("name"); // Retrieve the OAuth2 username
            email = oauthUser.getAttribute("email"); // Retrieve the OAuth2 email
        }
        // Check if the authenticated user is a regular user (UserDetails)
        else if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername(); // Retrieve the regular username
        }

        // If the username is found, pass the data to the model and return the profile view
        if (username != null) {
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            return "profile"; // Return the profile view (e.g., profile.html or profile.jsp)
        }

        // If no authenticated user is found, redirect to the login page
        return "redirect:/login";
    }

    // Method to handle user logout and redirect to the login page with a logout message
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout"; // Redirect to login page with a logout parameter
    }
}
