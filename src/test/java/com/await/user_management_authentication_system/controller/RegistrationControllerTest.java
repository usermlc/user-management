package com.await.user_management_authentication_system.controller; // Package where the test class is located

import com.await.user_management_authentication_system.dto.UserRegistrationDto; // Importing UserRegistrationDto for handling user registration data
import com.await.user_management_authentication_system.service.UserService; // Importing UserService to handle business logic
import org.junit.jupiter.api.BeforeEach; // Importing JUnit annotation for methods that run before each test
import org.junit.jupiter.api.Test; // Importing JUnit annotation to define test methods
import org.mockito.InjectMocks; // Importing Mockito annotation to inject mocks into the tested object
import org.mockito.Mock; // Importing Mockito annotation for mocking dependencies
import org.mockito.MockitoAnnotations; // Importing Mockito utility to initialize mocks
import org.springframework.ui.Model; // Importing Spring's Model interface to pass data to views

import static org.junit.jupiter.api.Assertions.assertEquals; // Importing JUnit's assertion method for checking expected results
import static org.mockito.ArgumentMatchers.any; // Importing ArgumentMatchers to handle method argument matching in mock tests
import static org.mockito.Mockito.*; // Importing Mockito methods for mocking and verification

class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController; // Injecting mock dependencies into RegistrationController

    @Mock
    private UserService userService; // Mocking UserService dependency

    @Mock
    private Model model; // Mocking Model object to verify interactions with the view

    // Set up test environment and initialize mocks before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocked objects
    }

    // Test for successful user registration
    @Test
    void testRegisterUserSuccess() {
        // Creating a sample UserRegistrationDto object
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("testuser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        // Simulate successful registration by making the service do nothing (no exception thrown)
        doNothing().when(userService).registerUser(any(UserRegistrationDto.class));

        // Call the registerUser method with the userDto and model
        String result = registrationController.registerUser(userDto, model);

        // Assert that the method redirects to the login page upon successful registration
        assertEquals("redirect:/login", result);
        // Verify that the registerUser method was called once with the correct argument
        verify(userService, times(1)).registerUser(userDto);
    }

    // Test for failed user registration (simulating an exception during registration)
    @Test
    void testRegisterUserFailure() {
        // Creating a sample UserRegistrationDto object
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("testuser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        // Simulate a failure by throwing an exception when the registration is attempted
        doThrow(new RuntimeException("Registration failed")).when(userService).registerUser(any(UserRegistrationDto.class));

        // Call the registerUser method, expecting it to handle the exception
        String result = registrationController.registerUser(userDto, model);

        // Assert that the registration page is returned when registration fails
        assertEquals("registration", result);
        // Verify that the error message was added to the model
        verify(model, times(1)).addAttribute("error", "Registration failed: Registration failed");
    }
}
