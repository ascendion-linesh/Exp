package com.example.userservice.talonone;

import com.example.userservice.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class TalonOneClient {
    @Value("${talonone.api.url}")
    private String talonOneApiUrl;

    @Value("${talonone.api.key}")
    private String talonOneApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void registerUser(User user) {
        String url = talonOneApiUrl + "/v1/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "ApiKey " + talonOneApiKey);
        Map<String, Object> body = new HashMap<>();
        body.put("id", user.getId());
        body.put("name", user.getName());
        body.put("email", user.getEmail());
        body.put("phone", user.getPhone());
        body.put("totalOrders", user.getTotalOrders());
        body.put("totalSpent", user.getTotalSpent());
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        try {
            restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            // Log and handle error gracefully
            System.err.println("Failed to register user with Talon.One: " + e.getMessage());
        }
    }
}
