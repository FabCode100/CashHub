package com.cashub.cashhubbackend.cashub.service;

import com.cashub.cashhubbackend.cashub.domain.user.User;
import com.cashub.cashhubbackend.cashub.repository.UserRepository;
import com.cashub.cashhubbackend.cashub.domain.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // For password hashing

    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println("Encoded password: " + encodedPassword);  // Log para verificação

        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


    public User getUser(Long id) {
        // 1. Find user by id
        Optional<User> userOptional = userRepository.findById(id);

        // 2. Check if user exists
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        // 3. Return user
        return userOptional.get();
    }

    public List<User> getAllUsers() {
        // 1. Find all users
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        // 1. Find user by id (optional, can be done based on the user object itself)
        Long userId = user.getId();
        // Optional<User> existingUserOptional = userRepository.findById(userId);

        // 2. Update user details (excluding password, which should be handled separately)
        // ... (you can update specific fields based on your needs)

        // 3. Save updated user to database
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        // 1. Find user by id
        Optional<User> userOptional = userRepository.findById(id);

        // 2. Check if user exists
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        // 3. Delete user (consider handling associated accounts)
        userRepository.deleteById(id);
    }

    // Additional methods specific to user management can be added here
    // For example, find user by email, password reset functionalities etc.
}
