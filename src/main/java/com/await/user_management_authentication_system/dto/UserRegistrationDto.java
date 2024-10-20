package com.await.user_management_authentication_system.dto; // Package where the DTO class is located

import lombok.Data; // Importing Lombok annotation to automatically generate getters, setters, and other common methods

@Data // Lombok annotation to generate boilerplate code (getters, setters, equals, hashCode, toString)
public class UserRegistrationDto {

    // Fields representing user registration details
    private String username;
    private String password;
    private String email;
}
