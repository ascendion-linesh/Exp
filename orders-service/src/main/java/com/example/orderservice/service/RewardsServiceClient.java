package com.example.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@FeignClient(name = "rewards-service", url = "${rewards.service.url}")
public interface RewardsServiceClient {
    @PostMapping("/rewards/calculate-discount")
    ResponseEntity<Map<String, Object>> calculateDiscount(@RequestBody Map<String, Object> cartData);
}
