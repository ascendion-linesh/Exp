package com.example.rewardservice.service;

import com.example.rewardservice.dto.OrderEventDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventListener {
    private final RewardsService rewardsService;
    private final Logger logger = LoggerFactory.getLogger(OrderEventListener.class);

    @KafkaListener(topics = "orders", groupId = "rewards-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenOrderEvents(OrderEventDto eventDto) {
        logger.info("Received order event from Kafka for orderId: {}", eventDto.getOrderId());
        // Idempotency can be added here if needed
        rewardsService.processOrderEvent(eventDto);
    }
}
