package com.app.controller;

import com.app.model.ProfileDTO;
import com.app.model.User;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller for managing users.
 * Provides endpoints for fetching and updating user data.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Fetches a user by their unique ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A ResponseEntity containing the found User and an HTTP 200 (OK) status.
     *         If the user is not found, a global exception handler should return a 404 (Not Found) status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates a user's profile statistics, such as total orders and total spent.
     *
     * @param id The ID of the user to update.
     * @param profileDTO The DTO containing the updated user statistics.
     * @return A ResponseEntity containing the updated User and an HTTP 200 (OK) status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody @Valid ProfileDTO profileDTO) {
        User updatedUser = userService.updateUserProfile(id, profileDTO);
        return ResponseEntity.ok(updatedUser);
    }
}