package com.ascendion.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest {
    @NotNull
    private Long userId;

    @NotEmpty
    private List<String> items;

    @NotNull
    private BigDecimal totalAmount;
}
