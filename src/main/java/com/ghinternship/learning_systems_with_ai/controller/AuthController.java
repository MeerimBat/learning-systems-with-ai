package com.ghinternship.learning_systems_with_ai.controller;

import com.ghinternship.learning_systems_with_ai.dto.UserLoginDto;
import com.ghinternship.learning_systems_with_ai.dto.UserRegistrationDto;
import com.ghinternship.learning_systems_with_ai.model.User;
import com.ghinternship.learning_systems_with_ai.service.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")

    public ResponseEntity<String> register(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.registerUser(registrationDto);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @NonNull UserLoginDto loginDto) {
        User user = userService.findByUsername(loginDto.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist.");
        }

        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash());
        if(!matches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Authorization successful");
    }

}
