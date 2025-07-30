package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        logger.info("Received order placement request for userId: {}", orderRequestDto.getUserId());
        OrderResponseDto response = orderService.placeOrder(orderRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
