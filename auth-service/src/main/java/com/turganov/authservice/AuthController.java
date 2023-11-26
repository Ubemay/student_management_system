package com.turganov.authservice;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AuthController {

    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        logger.info("Received login request for email: {}", loginRequest.getEmail());
        boolean success = authService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        return success ? "Login successful" : "Login failed";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
        return "Registration successful";
    }
}
