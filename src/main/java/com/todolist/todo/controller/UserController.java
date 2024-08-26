package com.todolist.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todolist.todo.model.User;
import com.todolist.todo.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/usertodo")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Signs up a new user.
     *
     * @param user the user object containing user details
     * @return the newly created user
     */
    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    /**
     * Logs in a user with the provided email and password.
     *
     * @param loginData a map containing 'email' and 'password' keys
     * @return a response entity containing a JWT token if login is successful,
     *         or an error message if login fails
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<String> jwtToken = userService.loginUser(email, password);

        Map<String, String> response = new HashMap<>();
        if (jwtToken.isPresent()) {
            response.put("token", jwtToken.get());
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(401).body(response);
        }
    }
}
