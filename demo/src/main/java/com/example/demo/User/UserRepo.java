package com.example.demo.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepo extends JpaRepository<User, String> {

    
    Optional<User> findByEmail(String email);

    
    void deleteByUsername(String username);

    
    boolean existsByUsername(String username);

    User findByUsername(String username);

}
