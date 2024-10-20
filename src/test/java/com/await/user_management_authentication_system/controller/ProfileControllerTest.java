package com.await.user_management_authentication_system.controller; // Package where the test class is located

import org.junit.jupiter.api.BeforeEach; // Importing JUnit annotation for methods that run before each test
import org.junit.jupiter.api.Test; // Importing JUnit annotation to define test methods
import org.mockito.Mock; // Importing Mockito annotation for mocking dependencies
import org.mockito.MockitoAnnotations; // Importing Mockito utility to initialize mocks
import org.springframework.ui.Model; // Importing Spring's Model interface to pass data to views
import org.springframework.security.core.userdetails.UserDetails; // Importing Spring Security's UserDetails to represent the authenticated user

import static org.junit.jupiter.api.Assertions.assertEquals; // Importing JUnit's assertion method for checking expected results
import static org.mockito.Mockito.*; // Importing Mockito methods for mocking and verification

class ProfileControllerTest {

    private ProfileController profileController; // Declaring the ProfileController to be tested

    @Mock
    private Model model; // Mocking the Model object to verify interactions

    // Method to set up the test environment before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializing the mocked objects
        profileController = new ProfileController(); // Creating an instance of ProfileController
    }

    // Test for the profile method when UserDetails is provided
    @Test
    void profile_WithUserDetails() {
        UserDetails userDetails = mock(UserDetails.class); // Mocking the UserDetails object
        when(userDetails.getUsername()).thenReturn("testuser"); // Mocking the return value for getUsername()

        String result = profileController.profile(userDetails, model); // Calling the profile method with the mocked userDetails

        verify(model, times(1)).addAttribute("username", "testuser"); // Verifying that the model received the correct username
        assertEquals("profile", result); // Asserting that the returned view name is "profile"
    }

    // Test for the profile method when the principal is null (no authenticated user)
    @Test
    void profile_WithNullPrincipal() {
        String result = profileController.profile(null, model); // Calling the profile method with a null principal

        assertEquals("redirect:/login", result); // Asserting that the user is redirected to the login page
    }
}
