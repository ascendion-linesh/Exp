package com.example.rewards.service;

import com.example.rewards.model.RewardResponse;
import com.example.rewards.model.TalonOneSessionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class RewardsService {
    private static final Logger logger = LoggerFactory.getLogger(RewardsService.class);
    private final TalonOneService talonOneService;
    // For demo purposes, using in-memory idempotency. In production, use persistent storage.
    private final Set<String> processedRequests = new HashSet<>();

    @Autowired
    public RewardsService(TalonOneService talonOneService) {
        this.talonOneService = talonOneService;
    }

    public RewardResponse evaluateRewards(TalonOneSessionRequest request) {
        String idempotencyKey = generateIdempotencyKey(request);
        synchronized (processedRequests) {
            if (processedRequests.contains(idempotencyKey)) {
                logger.info("Duplicate request detected for key: {}", idempotencyKey);
                // Return a generic response or fetch from cache if available
                return new RewardResponse();
            }
            processedRequests.add(idempotencyKey);
        }
        logger.info("Evaluating rewards for userId: {}", request.getUserId());
        return talonOneService.evaluateSession(request);
    }

    private String generateIdempotencyKey(TalonOneSessionRequest request) {
        // Simple key: userId + cart hash
        return request.getUserId() + "-" + request.cartHash();
    }
}
