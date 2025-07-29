package com.example.orderservice.feign.dto;

import com.example.orderservice.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountRequestDto {
    private Long userId;
    private List<CartItemDto> cartItems;
}
