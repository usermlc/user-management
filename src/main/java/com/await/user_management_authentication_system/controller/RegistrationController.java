package com.await.user_management_authentication_system.controller; // Package where the controller class is located

import com.await.user_management_authentication_system.dto.UserRegistrationDto; // Importing DTO to capture user registration data
import com.await.user_management_authentication_system.service.UserService; // Importing UserService to handle business logic
import org.springframework.stereotype.Controller; // Importing Controller annotation to mark this class as a Spring MVC controller
import org.springframework.ui.Model; // Importing Model to pass data to the view
import org.springframework.web.bind.annotation.*; // Importing annotations for mapping HTTP requests

@Controller // Marks this class as a Spring MVC controller
@RequestMapping("/registration") // Base URL for the registration endpoints
public class RegistrationController {

    private final UserService userService; // Declaring UserService to handle registration logic

    // Constructor for injecting the UserService dependency
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // Method to display the registration form
    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto()); // Adding an empty UserRegistrationDto to the model
        return "registration"; // Returning the view for the registration form (e.g., registration.html or registration.jsp)
    }

    // Method to handle form submission for user registration
    @PostMapping
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userDto, Model model) {
        try {
            // Attempt to register the user using the UserService
            userService.registerUser(userDto);
            return "redirect:/login"; // Redirect to the login page upon successful registration
        } catch (Exception e) {
            // If an error occurs, add the error message to the model and reload the registration page
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "registration"; // Return the registration view with the error message
        }
    }
}
