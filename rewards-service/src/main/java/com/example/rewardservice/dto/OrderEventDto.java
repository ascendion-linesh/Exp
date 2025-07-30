package com.example.rewardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDto {
    private String eventType;
    private String orderId;
    private String userId;
    private List<LoyaltyActionDto> loyaltyActions;
}
