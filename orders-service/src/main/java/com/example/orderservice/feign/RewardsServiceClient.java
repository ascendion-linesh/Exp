package com.example.orderservice.feign;

import com.example.orderservice.dto.DiscountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "rewards-service", url = "${rewards-service.url}")
public interface RewardsServiceClient {
    @GetMapping("/rewards/discount")
    DiscountResponseDto getDiscount(@RequestParam("userId") Long userId, @RequestParam("amount") BigDecimal amount);
}
