package com.example.orderservice.service;

import com.example.orderservice.dto.OrderEvent;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;
    private final RewardsServiceClient rewardsServiceClient;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        // Validate user
        var userResponse = userServiceClient.getUserById(orderRequestDto.getUserId());
        if (!userResponse.getStatusCode().is2xxSuccessful() || userResponse.getBody() == null) {
            logger.error("User not found for id: {}", orderRequestDto.getUserId());
            throw new IllegalArgumentException("User not found");
        }

        // Calculate discount
        Map<String, Object> cartData = new HashMap<>();
        cartData.put("userId", orderRequestDto.getUserId());
        cartData.put("cartItems", orderRequestDto.getCartItems());
        var discountResponse = rewardsServiceClient.calculateDiscount(cartData);
        double discount = 0.0;
        if (discountResponse.getStatusCode().is2xxSuccessful() && discountResponse.getBody() != null) {
            Object discountObj = discountResponse.getBody().get("discount");
            if (discountObj instanceof Number) {
                discount = ((Number) discountObj).doubleValue();
            }
        }

        // Calculate total amount (sum of product * quantity, assuming price is fetched elsewhere or mocked)
        double totalAmount = orderRequestDto.getCartItems().stream()
                .mapToDouble(item -> 50.0 * item.getQuantity()) // Mock price as 50.0 per item
                .sum();

        // Save order
        Order order = new Order(null, orderRequestDto.getUserId(), orderRequestDto.getCartItems(), totalAmount, discount, null);
        Order savedOrder = orderRepository.save(order);

        // Publish event to Kafka
        OrderEvent event = new OrderEvent(savedOrder.getId(), savedOrder.getUserId(), savedOrder.getTotalAmount(), savedOrder.getDiscount(), savedOrder.getCreatedAt());
        kafkaTemplate.send("orders", event);
        logger.info("Order event published to Kafka for orderId: {}", savedOrder.getId());

        return new OrderResponseDto(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getCartItems(),
                savedOrder.getTotalAmount(),
                savedOrder.getDiscount(),
                savedOrder.getCreatedAt()
        );
    }
}
