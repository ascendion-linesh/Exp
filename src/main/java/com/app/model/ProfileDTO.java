package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for transferring user profile data. Used for updating user statistics
 * and for sending profile attributes to Talon.One.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    /**
     * The user's total number of completed orders.
     */
    private int totalOrders;

    /**
     * The user's lifetime spending.
     */
    private BigDecimal totalSpent;

    /**
     * The user's loyalty level.
     */
    private String loyaltyLevel;
}