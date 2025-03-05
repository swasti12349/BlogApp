package com.example.demo.User;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Welcome to the home page");
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.status(201).body(userDto); // 201 Created for POST requests
}


    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return ResponseEntity.ok("User updated successfully!");
    }


    @PostMapping("/deleteUser")
    public void deleteUser(@RequestBody String email) {
        userService.deleteUser(email);
    }

    @GetMapping("/getUser")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        userService.getUser(username);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

}