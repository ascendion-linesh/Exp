package com.example.rewards.controller;

import com.example.rewards.model.RewardResponse;
import com.example.rewards.model.TalonOneSessionRequest;
import com.example.rewards.service.RewardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    @Autowired
    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @Operation(summary = "Evaluate discounts and rewards for a user's cart", description = "Evaluates the cart for discounts and rewards based on userId and cartItems.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Discounts and rewards evaluated successfully", content = @Content(schema = @Schema(implementation = RewardResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/evaluate")
    public ResponseEntity<RewardResponse> evaluateRewards(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request containing userId and cartItems", required = true, content = @Content(schema = @Schema(implementation = TalonOneSessionRequest.class)))
                                                         @RequestBody TalonOneSessionRequest request) {
        RewardResponse response = rewardsService.evaluateRewards(request);
        return ResponseEntity.ok(response);
    }
}
