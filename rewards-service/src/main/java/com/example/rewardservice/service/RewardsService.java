package com.example.rewardservice.service;

import com.example.rewardservice.dto.CartEvaluationRequestDto;
import com.example.rewardservice.dto.CartEvaluationResponseDto;
import com.example.rewardservice.dto.OrderEventDto;
import com.example.rewardservice.talonone.TalonOneClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardsService {
    private final TalonOneClient talonOneClient;
    private final Logger logger = LoggerFactory.getLogger(RewardsService.class);

    public CartEvaluationResponseDto evaluateCart(CartEvaluationRequestDto requestDto) {
        logger.info("Evaluating cart for userId: {}", requestDto.getUserId());
        return talonOneClient.evaluateCartSession(requestDto);
    }

    public void processOrderEvent(OrderEventDto eventDto) {
        logger.info("Processing order event for orderId: {}", eventDto.getOrderId());
        talonOneClient.confirmLoyaltyAction(eventDto);
    }
}
