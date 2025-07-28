package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO for creating a new order. It contains the user's identifier
 * and the list of items they wish to purchase.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    /**
     * The unique identifier for the user placing the order.
     * This corresponds to the Talon.One profile ID.
     */
    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    /**
     * The list of items in the order.
     */
    @NotEmpty(message = "Order must contain at least one item")
    private List<Item> items;
}