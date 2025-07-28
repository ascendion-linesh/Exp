package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Represents an item within an order. This is an embeddable entity,
 * meaning its data is stored within the 'customer_orders' table itself.
 * It is also used as a data transfer object in requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Item {

    /**
     * The unique stock-keeping unit (SKU) of the item.
     */
    @NotBlank(message = "Item SKU cannot be blank")
    private String sku;

    /**
     * The display name of the item.
     */
    @NotBlank(message = "Item name cannot be blank")
    private String name;

    /**
     * The price of a single unit of the item.
     */
    @NotNull(message = "Item price cannot be null")
    private BigDecimal price;

    /**
     * The number of units of this item in the order.
     */
    @Min(value = 1, message = "Item quantity must be at least 1")
    private int quantity;
}