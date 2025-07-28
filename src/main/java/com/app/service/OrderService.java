package com.app.service;

import com.app.model.*;
import com.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Service layer for managing orders.
 * This class orchestrates the entire order placement process, coordinating between
 * user management, rewards evaluation, and data persistence.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final RewardsService rewardsService;

    /**
     * Places a new order by executing a series of steps within a single transaction.
     * The process involves fetching user data, evaluating the cart for discounts,
     * saving the order, updating user statistics, and confirming the transaction with the rewards service.
     *
     * @param req The request object containing order details.
     * @return The newly created Order entity.
     */
    @Transactional
    public Order placeOrder(OrderRequest req) {
        // 1. Retrieve the user placing the order.
        User user = userService.getUser(req.getUserId());

        // 2. Evaluate the cart for discounts and rewards with Talon.One.
        CartRequest cartRequest = createCartRequest(req, user);
        RewardsResponse rewardsResponse = rewardsService.evaluate(cartRequest);

        // 3. Create the order and calculate totals.
        Order order = new Order();
        order.setUser(user);
        order.setItems(req.getItems());
        order.setCreatedAt(Instant.now());

        BigDecimal originalTotal = req.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setOriginalTotal(originalTotal);

        // Assume RewardsResponse provides the total discount amount.
        BigDecimal discountAmount = rewardsResponse.getDiscountAmount();
        BigDecimal finalTotal = originalTotal.subtract(discountAmount);
        order.setDiscountAmount(discountAmount);
        order.setFinalTotal(finalTotal);

        Order savedOrder = orderRepository.save(order);

        // 4. Update user's lifetime statistics.
        user.setTotalOrders(user.getTotalOrders() + 1);
        user.setTotalSpent(user.getTotalSpent().add(finalTotal));
        userService.save(user);

        // 5. Confirm the transaction with Talon.One to finalize loyalty points.
        rewardsService.confirmLoyalty(req.getUserId(), finalTotal.doubleValue());

        return savedOrder;
    }

    /**
     * Helper method to construct a CartRequest from an OrderRequest.
     * This maps application-specific data to the format expected by the RewardsService
     * and Talon.One.
     *
     * @param req The incoming order request.
     * @param user The user associated with the order.
     * @return A CartRequest object populated with data for evaluation.
     */
    private CartRequest createCartRequest(OrderRequest req, User user) {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setProfileId(req.getUserId());

        // Populate profile attributes (e.g., from the User object).
        Map<String, Object> profileAttributes = new HashMap<>();
        profileAttributes.put("loyaltyLevel", user.getLoyaltyLevel()); // Example attribute
        cartRequest.setProfileAttributes(profileAttributes);

        // Populate session attributes (e.g., cart items).
        Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put("cartItems", req.getItems()); // Example attribute
        cartRequest.setSessionAttributes(sessionAttributes);

        return cartRequest;
    }
}