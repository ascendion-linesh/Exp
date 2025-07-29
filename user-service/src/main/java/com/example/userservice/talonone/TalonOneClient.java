package com.example.userservice.talonone;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TalonOneClient {
    @Value("${talonone.api.url}")
    private String talonOneApiUrl;

    @Value("${talonone.api.key}")
    private String talonOneApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void registerUserWithTalonOne(String integrationId, String name, String email, String phone) {
        String url = talonOneApiUrl + "/v1/customer_profiles";
        Map<String, Object> body = new HashMap<>();
        body.put("integrationId", integrationId);
        body.put("attributes", Map.of(
                "name", name,
                "email", email,
                "phone", phone
        ));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "ApiKey-v1 " + talonOneApiKey);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
    }
}
