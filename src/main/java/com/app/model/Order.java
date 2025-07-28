package com.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Represents a customer order in the e-commerce system.
 * This entity is persisted in the database and contains details about the transaction,
 * including items, costs, and applied discounts.
 */
@Entity
@Table(name = "customer_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * The unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who placed the order.
     * This establishes a many-to-one relationship with the User entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference // Manages serialization to prevent infinite loops
    private User user;

    /**
     * The timestamp when the order was created.
     */
    private Instant createdAt;

    /**
     * The total cost of the order before any discounts are applied.
     */
    private BigDecimal originalTotal;

    /**
     * The total amount of discount applied to the order, calculated by Talon.One.
     */
    private BigDecimal discountAmount;

    /**
     * The final cost of the order after discounts have been applied.
     */
    private BigDecimal finalTotal;

    /**
     * The list of items included in this order.
     * This is an ElementCollection, meaning the items are stored as part of the order record.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<Item> items;
}