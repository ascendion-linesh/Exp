package com.app.service;

import com.app.model.CartRequest;
import com.app.model.RewardsResponse;
import com.app.talonone.TalonOneClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service layer for interacting with the Talon.One rewards engine.
 * This class abstracts the communication with the Talon.One API for evaluating
 * rewards and confirming transactions.
 */
@Service
@RequiredArgsConstructor
public class RewardsService {

    private final TalonOneClient talonOneClient;

    /**
     * Evaluates a customer's cart with Talon.One to get applicable discounts and rewards.
     * This involves updating the customer's profile and then evaluating their session.
     *
     * @param cartRequest The request containing profile and session data for evaluation.
     * @return A RewardsResponse containing the effects calculated by Talon.One.
     */
    public RewardsResponse evaluate(CartRequest cartRequest) {
        // First, update the customer profile in Talon.One with any new attributes.
        talonOneClient.updateProfile(cartRequest.getProfileId(), cartRequest.getProfileAttributes());

        // Then, evaluate the customer session to determine applicable rules and effects.
        return talonOneClient.evaluateSession(cartRequest.getProfileId(), cartRequest.getSessionAttributes());
    }

    /**
     * Confirms a transaction with Talon.One, typically after an order is successfully placed.
     * This finalizes the application of loyalty points (earn/burn).
     *
     * @param userId The unique identifier for the user's profile in Talon.One.
     * @param total The final total of the transaction.
     */
    public void confirmLoyalty(String userId, double total) {
        // Delegate the call to the Talon.One client to confirm the transaction.
        talonOneClient.confirmLoyalty(userId, total);
    }
}