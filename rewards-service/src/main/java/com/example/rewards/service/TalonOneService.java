package com.example.rewards.service;

import com.example.rewards.config.TalonOneConfig;
import com.example.rewards.exception.TalonOneException;
import com.example.rewards.model.RewardResponse;
import com.example.rewards.model.TalonOneSessionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TalonOneService {
    private static final Logger logger = LoggerFactory.getLogger(TalonOneService.class);

    private final TalonOneConfig talonOneConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public TalonOneService(TalonOneConfig talonOneConfig) {
        this.talonOneConfig = talonOneConfig;
        this.restTemplate = new RestTemplate();
    }

    public RewardResponse evaluateSession(TalonOneSessionRequest request) {
        String url = talonOneConfig.getBaseUrl() + "/v1/applications/" + talonOneConfig.getApplicationId() + "/session";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "ApiKey-v1 " + talonOneConfig.getApiKey());
        HttpEntity<TalonOneSessionRequest> entity = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<RewardResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    RewardResponse.class
            );
            logger.info("Talon.One session evaluated for userId: {}", request.getUserId());
            return response.getBody();
        } catch (RestClientException ex) {
            logger.error("Error communicating with Talon.One API: {}", ex.getMessage(), ex);
            throw new TalonOneException("Failed to evaluate Talon.One session", ex);
        }
    }
}
