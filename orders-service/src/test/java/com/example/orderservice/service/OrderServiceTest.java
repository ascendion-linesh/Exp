package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.entity.CartItem;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserServiceClient userServiceClient;
    @Mock
    private RewardsServiceClient rewardsServiceClient;
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_Success() {
        OrderRequestDto request = new OrderRequestDto(1L, List.of(new CartItem("p1", 2)));
        when(userServiceClient.getUserById(1L)).thenReturn(ResponseEntity.ok(Map.of("id", 1L)));
        when(rewardsServiceClient.calculateDiscount(any())).thenReturn(ResponseEntity.ok(Map.of("discount", 10.0)));
        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> {
            Order o = inv.getArgument(0);
            o.setId(100L);
            return o;
        });

        var response = orderService.placeOrder(request);
        assertNotNull(response);
        assertEquals(100L, response.getOrderId());
        assertEquals(1L, response.getUserId());
        assertEquals(10.0, response.getDiscount());
        verify(kafkaTemplate, times(1)).send(eq("orders"), any());
    }

    @Test
    void testPlaceOrder_UserNotFound() {
        OrderRequestDto request = new OrderRequestDto(2L, List.of(new CartItem("p2", 1)));
        when(userServiceClient.getUserById(2L)).thenReturn(ResponseEntity.notFound().build());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(request));
        assertEquals("User not found", ex.getMessage());
    }
}
