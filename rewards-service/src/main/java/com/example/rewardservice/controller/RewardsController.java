package com.example.rewardservice.controller;

import com.example.rewardservice.dto.CartEvaluationRequestDto;
import com.example.rewardservice.dto.CartEvaluationResponseDto;
import com.example.rewardservice.service.RewardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
@Tag(name = "Rewards API", description = "Endpoints for evaluating discounts and rewards")
public class RewardsController {
    private final RewardsService rewardsService;
    private final Logger logger = LoggerFactory.getLogger(RewardsController.class);

    @PostMapping("/evaluate")
    @Operation(summary = "Evaluate cart session for discounts and rewards")
    public ResponseEntity<CartEvaluationResponseDto> evaluateCart(@RequestBody CartEvaluationRequestDto requestDto) {
        logger.info("Received cart evaluation request for userId: {}", requestDto.getUserId());
        CartEvaluationResponseDto response = rewardsService.evaluateCart(requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
