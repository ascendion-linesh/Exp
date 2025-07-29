package com.example.userservice.talonone;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.exception.TalonOneIntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class TalonOneService {
    @Value("${talonone.api.url}")
    private String talonOneApiUrl;

    @Value("${talonone.api.key}")
    private String talonOneApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void registerUserWithTalonOne(UserRequestDto userDto) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "ApiKey " + talonOneApiKey);

            Map<String, Object> body = new HashMap<>();
            body.put("profileId", userDto.getEmail());
            body.put("attributes", Map.of(
                "name", userDto.getName(),
                "email", userDto.getEmail(),
                "phone", userDto.getPhone(),
                "totalOrders", userDto.getTotalOrders(),
                "totalSpent", userDto.getTotalSpent()
            ));

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(
                talonOneApiUrl + "/v1/customer_profiles",
                request,
                String.class
            );
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new TalonOneIntegrationException("Failed to register user with Talon.One");
            }
        } catch (Exception ex) {
            throw new TalonOneIntegrationException("Talon.One integration failed", ex);
        }
    }
}
