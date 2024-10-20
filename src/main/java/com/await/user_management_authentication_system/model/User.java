package com.await.user_management_authentication_system.model; // Package where the model class is located

import jakarta.persistence.Entity; // Importing Entity annotation to mark this class as a JPA entity
import jakarta.persistence.GeneratedValue; // Importing annotation for generating primary key values
import jakarta.persistence.GenerationType; // Importing strategy for how the primary key is generated
import jakarta.persistence.Id; // Importing Id annotation to mark the primary key field
import jakarta.persistence.Table; // Importing Table annotation to specify the database table name
import lombok.AllArgsConstructor; // Importing Lombok annotation to generate a constructor with all fields
import lombok.Data; // Importing Lombok annotation to automatically generate getters, setters, and other common methods
import lombok.NoArgsConstructor; // Importing Lombok annotation to generate a no-argument constructor

@Data // Lombok annotation to automatically generate boilerplate code (getters, setters, equals, hashCode, toString)
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
@Entity // Marking this class as a JPA entity that will be mapped to a database table
@Table(name = "users") // Specifying the table name in the database as "users"
public class User {

    @Id // Marking this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the primary key using the database's identity column
    private Long id;

    // Fields representing user information: username, password, email, and OAuth2-related fields
    private String username;
    private String password;
    private String email;
    private String oauth2Provider; // Field for storing OAuth2 provider information (e.g., Google, Facebook)
    private String oauth2Id; // Field for storing the user's ID from the OAuth2 provider
}
