package com.example.UserAPIMongoDB.Repositories;

import com.example.UserAPIMongoDB.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, Integer> {
    User findByEmail(String email); //This method is used to find a user by email (CONVENTION OF SPRING DATA JPA)
}
