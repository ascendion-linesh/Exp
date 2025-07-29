package com.ascendion.order.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private List<String> items;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private String status;
    private LocalDateTime createdAt;
}
