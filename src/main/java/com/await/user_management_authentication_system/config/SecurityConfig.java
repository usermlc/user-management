package com.await.user_management_authentication_system.config; // Package where the security configuration is located

import org.springframework.context.annotation.Bean; // Importing Bean annotation to define beans in the Spring context
import org.springframework.context.annotation.Configuration; // Importing Configuration annotation to mark this class as a Spring configuration class
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Importing HttpSecurity for configuring web security
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Enabling Spring Security's web security support
import org.springframework.security.core.userdetails.User; // Importing User class for creating in-memory users
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Importing BCryptPasswordEncoder for password hashing
import org.springframework.security.crypto.password.PasswordEncoder; // Interface for encoding passwords
import org.springframework.security.provisioning.InMemoryUserDetailsManager; // Importing InMemoryUserDetailsManager for managing in-memory user details
import org.springframework.security.provisioning.UserDetailsManager; // Importing UserDetailsManager to manage user details
import org.springframework.security.web.SecurityFilterChain; // Importing SecurityFilterChain to define the security configuration

@Configuration // Marks this class as a configuration class in Spring
@EnableWebSecurity // Enables Spring Security support for web applications
public class SecurityConfig {

    // Configuring security filter chain for HTTP requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // Allow access to registration, login, and CSS files without authentication
                .requestMatchers("/registration", "/login", "/css/**").permitAll()
                .anyRequest().authenticated() // Require authentication for any other request
            )
            .formLogin(form -> form
                .loginPage("/login") // Specify custom login page
                .defaultSuccessUrl("/profile", true) // Redirect to the profile page upon successful login
                .permitAll() // Allow access to the login page for all users
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login") // Use the same login page for OAuth2
                .defaultSuccessUrl("/oauth2/callback") // Redirect to the OAuth2 callback URL after login
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // Custom logout URL
                .logoutSuccessUrl("/login?logout") // Redirect to login page with logout message
                .invalidateHttpSession(true) // Invalidate session on logout
                .clearAuthentication(true) // Clear authentication on logout
                .permitAll() // Allow all users to access the logout URL
            );

        return http.build(); // Return the configured security filter chain
    }

    // Bean for encoding passwords using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt hashing algorithm for password encryption
    }

    // In-memory user details manager for demonstration purposes (creates a user with hardcoded credentials)
    @Bean
    public UserDetailsManager userDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
            .password(passwordEncoder().encode("password")) // Encoding password using BCrypt
            .roles("USER") // Assigning role USER to this user
            .build());
        return manager; // Returning the in-memory user manager
    }
}
