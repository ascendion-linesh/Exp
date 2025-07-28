package com.app.service;

import com.app.model.ProfileDTO;
import com.app.model.User;
import com.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Service layer for managing users.
 * This class encapsulates the business logic for user-related operations,
 * interacting with the UserRepository to fetch and persist user data.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Fetches a user by their primary key (Long).
     * This method is used by the UserController.
     *
     * @param id The ID of the user to retrieve.
     * @return The found User entity.
     * @throws EntityNotFoundException if no user is found with the given ID.
     */
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    /**
     * Fetches a user by their unique identifier (String).
     * This method is used by the OrderService, where the ID might be a
     * customer profile ID from an external system like Talon.One.
     *
     * @param id The ID of the user to retrieve.
     * @return The found User entity.
     * @throws EntityNotFoundException if no user is found.
     * @throws IllegalArgumentException if the ID format is invalid.
     */
    public User getUser(String id) {
        try {
            Long userId = Long.parseLong(id);
            return findById(userId);
        } catch (NumberFormatException e) {
            // Handle cases where the ID might not be a number, or look up by a different field.
            // For this example, we assume the string ID is a parseable Long.
            throw new IllegalArgumentException("Invalid user ID format: " + id);
        }
    }

    /**
     * Updates a user's profile statistics based on a DTO.
     * This method is used by the UserController.
     *
     * @param id The ID of the user to update.
     * @param profileDTO The DTO containing the updated statistics.
     * @return The updated User entity.
     */
    public User updateUserProfile(Long id, ProfileDTO profileDTO) {
        User user = findById(id);
        user.setTotalOrders(profileDTO.getTotalOrders());
        user.setTotalSpent(profileDTO.getTotalSpent());
        return userRepository.save(user);
    }

    /**
     * Saves or updates a user entity in the database.
     * This method is used by OrderService to persist changes to user statistics
     * after an order is placed.
     *
     * @param user The user entity to save.
     * @return The persisted User entity.
     */
    public User save(User user) {
        return userRepository.save(user);
    }
}