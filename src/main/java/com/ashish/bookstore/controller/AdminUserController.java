package com.ashish.bookstore.controller;

import com.ashish.bookstore.model.AppUser;
import com.ashish.bookstore.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    // View all users
    // Call using: GET /api/admin/users
    @GetMapping
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    // Remove a user
    // Call using: DELETE /api/admin/users/2
    @DeleteMapping("/{userId}")
    public String removeUser(@PathVariable Long userId) {
        if (appUserRepository.existsById(userId)) {
            appUserRepository.deleteById(userId);
            return "User removed successfully.";
        }
        return "User not found.";
    }
}