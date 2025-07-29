package com.ascendion.order.client;

import com.ascendion.order.dto.DiscountResponse;
import com.ascendion.order.dto.DiscountRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "rewards-service", url = "${rewards.service.url}")
public interface RewardsServiceClient {
    @PostMapping("/rewards/discount")
    DiscountResponse calculateDiscount(@RequestBody DiscountRequest request);
}
