package com.await.user_management_authentication_system.controller; // Package where the controller class is located

import org.springframework.stereotype.Controller; // Importing Controller annotation to mark this as a Spring MVC controller
import org.springframework.ui.Model; // Importing Model to pass data to the view
import org.springframework.web.bind.annotation.GetMapping; // Importing GetMapping to handle HTTP GET requests
import org.springframework.web.bind.annotation.RequestParam; // Importing RequestParam to capture request parameters

@Controller // Annotation indicating that this class is a Spring MVC controller
public class LoginController {

    // Method to handle GET requests to the "/login" URL and show the login form
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        // If an error parameter is present, add an error message to the model
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        // Return the name of the login view (e.g., login.html or login.jsp)
        return "login";
    }
}
