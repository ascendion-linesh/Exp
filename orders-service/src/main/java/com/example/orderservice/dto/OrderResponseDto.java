package com.example.orderservice.dto;

import com.example.orderservice.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private List<CartItem> cartItems;
    private Double totalAmount;
    private Double discount;
    private Instant createdAt;
}
