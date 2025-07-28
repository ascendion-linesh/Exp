package com.app.controller;

import com.app.model.CartRequest;
import com.app.model.RewardsResponse;
import com.app.service.RewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for handling rewards and discounts.
 * Integrates with Talon.One via the RewardsService.
 */
@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardsController {

    private final RewardsService rewardsService;

    /**
     * Evaluates a customer's cart to determine applicable rewards and discounts.
     *
     * @param cartRequest The request body containing the user's profile and cart items.
     * @return A ResponseEntity containing the RewardsResponse from Talon.One and an HTTP 200 (OK) status.
     */
    @PostMapping("/evaluate")
    public ResponseEntity<RewardsResponse> evaluateRewards(@RequestBody @Valid CartRequest cartRequest) {
        RewardsResponse rewardsResponse = rewardsService.evaluate(cartRequest);
        return ResponseEntity.ok(rewardsResponse);
    }
}