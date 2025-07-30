package com.example.userservice.talonone;

import com.example.userservice.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TalonOneClient {
    @Value("${talonone.api.url}")
    private String talonOneApiUrl;

    @Value("${talonone.api.key}")
    private String talonOneApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void registerUserWithTalonOne(User user) {
        String url = talonOneApiUrl + "/v1/customers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "ApiKey " + talonOneApiKey);
        String body = String.format("{\"integrationId\":\"%s\",\"attributes\":{\"email\":\"%s\",\"name\":\"%s\",\"phone\":\"%s\"}}", user.getId(), user.getEmail(), user.getName(), user.getPhone());
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to register user with Talon.One: " + response.getBody());
            }
        } catch (Exception e) {
            // Log error or handle gracefully
            throw new RuntimeException("Talon.One registration failed: " + e.getMessage(), e);
        }
    }
}
