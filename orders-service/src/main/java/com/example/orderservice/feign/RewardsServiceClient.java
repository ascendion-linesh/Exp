package com.example.orderservice.feign;

import com.example.orderservice.feign.dto.DiscountRequestDto;
import com.example.orderservice.feign.dto.DiscountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "rewards-service", url = "${rewards-service.url}")
public interface RewardsServiceClient {
    @PostMapping("/api/rewards/discount")
    DiscountResponseDto calculateDiscount(@RequestBody DiscountRequestDto requestDto);
}
