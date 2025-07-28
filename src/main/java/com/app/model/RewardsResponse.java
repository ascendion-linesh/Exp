package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO representing the response from the Talon.One rewards evaluation.
 * It includes the total discount amount and a list of all effects
 * triggered by the rules engine.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardsResponse {

    /**
     * The total discount amount calculated by Talon.One.
     */
    private BigDecimal discountAmount = BigDecimal.ZERO;

    /**
     * A list of effects triggered by the Talon.One rules engine.
     * Effects can include discounts, loyalty point additions, or custom actions.
     * Using Object to be flexible with various effect structures from the API.
     */
    private List<Object> effects;
}