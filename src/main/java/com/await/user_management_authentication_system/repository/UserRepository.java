package com.await.user_management_authentication_system.repository; // Package where the repository interface is located

import com.await.user_management_authentication_system.model.User; // Importing the User model class
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository to provide CRUD operations

// Repository interface for managing User entities in the database
public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find a user by their email
    User findByEmail(String email);
}
