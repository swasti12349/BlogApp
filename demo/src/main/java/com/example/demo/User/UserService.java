package com.example.demo.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    public User getUser(String email) {
        try {
            return modelMapper.map(userRepo.findByEmail(email).orElse(null), User.class);
        } catch (Exception e) {
            System.out.println("Error: User not found!");
            return null;
        }

    }

    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.isEmpty() ? Collections.emptyList() : users;
    }

    public void addUser(UserDto user) {
        userRepo.save(modelMapper.map(user, User.class));
    }

    public void deleteUser(String email) {
        try {
            userRepo.delete(modelMapper.map(userRepo.findByEmail(email).orElse(null), User.class));
        } catch (Exception e) {
            System.out.println("Error: User not found!");
        }
    }

    public String updateUser(UserDto userDto) {
        // Check if the user exists
        Optional<User> existingUserOptional = userRepo.findByEmail(userDto.getUsername());

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update only non-null fields (to avoid overwriting with null)
            if (userDto.getEmail() != null) {
                existingUser.setEmail(userDto.getEmail());
            }
            if (userDto.getRole() != null) {
                existingUser.setRole(userDto.getRole());
            }

            // Save the updated user
            userRepo.save(existingUser);

            return "User updated successfully!";
        } else {
            return "Error: User not found!";
        }
    }
}
