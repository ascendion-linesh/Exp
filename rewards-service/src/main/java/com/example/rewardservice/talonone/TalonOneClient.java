package com.example.rewardservice.talonone;

import com.example.rewardservice.dto.CartEvaluationRequestDto;
import com.example.rewardservice.dto.CartEvaluationResponseDto;
import com.example.rewardservice.dto.OrderEventDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TalonOneClient {
    private final WebClient webClient;

    public TalonOneClient(
            @Value("${talonone.api.url}") String talonOneApiUrl,
            @Value("${talonone.api.key}") String talonOneApiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(talonOneApiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "ApiKey " + talonOneApiKey)
                .build();
    }

    public CartEvaluationResponseDto evaluateCartSession(CartEvaluationRequestDto requestDto) {
        // This endpoint and payload should match your Talon.One API contract
        return webClient.post()
                .uri("/v1/campaigns/evaluate")
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(CartEvaluationResponseDto.class)
                .onErrorResume(e -> Mono.empty())
                .blockOptional()
                .orElse(new CartEvaluationResponseDto(requestDto.getUserId(), null, null));
    }

    public void confirmLoyaltyAction(OrderEventDto orderEventDto) {
        // This endpoint and payload should match your Talon.One API contract
        webClient.post()
                .uri("/v1/loyalty/confirm")
                .bodyValue(orderEventDto)
                .retrieve()
                .toBodilessEntity()
                .onErrorResume(e -> Mono.empty())
                .block();
    }
}
