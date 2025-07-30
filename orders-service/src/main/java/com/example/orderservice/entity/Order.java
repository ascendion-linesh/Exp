package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, columnDefinition = "jsonb")
    @Convert(converter = com.example.orderservice.entity.CartItemListConverter.class)
    private List<CartItem> cartItems;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private Double discount;

    @CreationTimestamp
    private Instant createdAt;
}
