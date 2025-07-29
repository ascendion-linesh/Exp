package com.ascendion.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscountResponse {
    private BigDecimal discountAmount;
    private String discountType;
}
