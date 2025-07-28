package com.app.controller;

import com.app.model.Order;
import com.app.model.OrderRequest;
import com.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for managing orders.
 * Handles the creation of new orders.
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Creates and processes a new order.
     * This method delegates the complex orchestration logic (evaluating rewards, saving the order,
     * updating user stats) to the OrderService to keep the controller thin.
     *
     * @param orderRequest The request body containing all necessary information to create an order.
     * @return A ResponseEntity containing the newly created Order and an HTTP 201 (Created) status.
     */
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Order createdOrder = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}