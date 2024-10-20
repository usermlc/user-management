# User Management Authentication System

A **Spring Boot** application for user management and authentication, supporting both traditional form-based login and OAuth2 login (e.g., Google).

## Features

- User registration and login
- Form-based and OAuth2 login (Google)
- Profile page for authenticated users
- Password encryption with BCrypt
- Secure routes for authenticated users

## Setup

1. Configure OAuth2 in `application.properties`:
   ```properties
   spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
   spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
   ```

2. Build and run the app:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Access the app at:
   ```
   http://localhost:8080
   ```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
