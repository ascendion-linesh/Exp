package com.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a user in the e-commerce system.
 * This entity is persisted in the database and tracks user-specific
 * information like order history and total spending.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The total number of orders placed by the user.
     * This is an attribute that could be used by Talon.One for segmentation.
     */
    private int totalOrders = 0;

    /**
     * The total amount of money spent by the user across all orders.
     * This is an attribute that could be used by Talon.One for segmentation.
     */
    private BigDecimal totalSpent = BigDecimal.ZERO;

    /**
     * The user's current loyalty level (e.g., "bronze", "silver", "gold").
     * This is a key attribute for personalized rewards in Talon.One.
     */
    private String loyaltyLevel;

    /**
     * A list of all orders placed by this user.
     * The relationship is lazily fetched to optimize performance.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Manages serialization to prevent infinite loops
    private List<Order> orders;
}