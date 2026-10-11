package com.ashish.bookstore.controller;

import com.ashish.bookstore.model.AppUser;
import com.ashish.bookstore.model.Role;
import com.ashish.bookstore.repository.AppUserRepository;
import com.ashish.bookstore.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        if (appUserRepository.findByEmail(request.email).isPresent()) {
            return "Email is already taken!";
        }

        AppUser user = new AppUser();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password)); // Hash the password

        // You can set logic here to assign ADMIN roles, but by default make them USER
        user.setRole(Role.USER);

        appUserRepository.save(user);
        return "User registered successfully!";
    }

    // LOGIN
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest request) {
        // This validates the credentials against the database
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email, request.password)
        );

        // If successful, fetch the user to get their role
        AppUser user = appUserRepository.findByEmail(request.email).get();

        // Generate and return the JWT
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    // Simple DTO classes for handling incoming JSON requests
    public static class RegisterRequest {
        public String name;
        public String email;
        public String password;
    }

    public static class LoginRequest {
        public String email;
        public String password;
    }
}