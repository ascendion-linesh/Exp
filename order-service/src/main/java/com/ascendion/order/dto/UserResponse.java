package com.ascendion.order.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String loyaltyTier;
}
