package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * DTO for sending cart and profile data to the RewardsService for evaluation.
 * It contains the user's profile ID and attributes that Talon.One will use
 * to calculate applicable discounts and rewards.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    /**
     * The Talon.One profile ID of the user.
     */
    private String profileId;

    /**
     * A map of customer profile attributes to be updated or used in Talon.One.
     * Example: {"loyaltyLevel": "gold", "totalSpent": 500.0}
     */
    private Map<String, Object> profileAttributes;

    /**
     * A map of session-specific attributes, such as cart items or coupon codes.
     * Example: {"cartItems": [...], "couponCode": "SUMMER10"}
     */
    private Map<String, Object> sessionAttributes;
}