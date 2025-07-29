package com.ascendion.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscountRequest {
    private Long userId;
    private BigDecimal orderAmount;
}
