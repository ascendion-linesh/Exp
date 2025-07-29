package com.example.rewards.service;

import com.example.rewards.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrderListener.class);

    @KafkaListener(topics = "order-events", groupId = "rewards-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenOrderEvents(OrderEvent orderEvent) {
        logger.info("Received order event: {}", orderEvent);
        // Confirm loyalty actions here (e.g., update database, call Talon.One if needed)
        // For demo, just log the event
        // In production, handle idempotency and error handling
    }
}
