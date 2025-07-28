package com.app.talonone;

import com.app.model.ProfileDTO;
import com.app.model.RewardsResponse;
import com.app.model.SessionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

/**
 * A client for interacting with the Talon.One Integration API.
 * This class provides a centralized and reusable component for managing customer profiles,
 * evaluating sessions for rewards, and confirming loyalty points within a Spring Boot application.
 * It handles HTTP communication, authentication, and data serialization/deserialization.
 * The client is designed to be thread-safe and reusable across services.
 */
@Component
public class TalonOneClient {

    @Value("${talonone.base-url}")
    private String baseUrl;

    @Value("${talonone.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    /**
     * Constructs a new TalonOneClient with the given RestTemplate.
     *
     * @param restTemplate The RestTemplate to use for making HTTP requests. It should be
     *                     configured as a Spring bean, potentially with custom error handlers
     *                     or interceptors.
     */
    public TalonOneClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Updates a customer profile in Talon.One with a given set of attributes.
     * This method sends a PUT request to the {@code /v1/profiles/{userId}} endpoint.
     *
     * @param userId The unique identifier of the customer profile to update.
     * @param dto    A DTO containing the profile attributes to update.
     * @throws org.springframework.web.client.HttpClientErrorException if Talon.One returns a 4xx error (e.g., Not Found, Bad Request).
     * @throws org.springframework.web.client.HttpServerErrorException if Talon.One returns a 5xx error (e.g., Internal Server Error).
     */
    public void updateProfile(String userId, ProfileDTO dto) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/v1/profiles/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        HttpEntity<ProfileDTO> requestEntity = new HttpEntity<>(dto, createAuthHeaders());

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
    }

    /**
     * Evaluates a customer session to determine applicable rewards, discounts, and other effects.
     * This method sends a POST request to the {@code /v1/sessions} endpoint.
     *
     * @param dto A DTO containing the session details, including customer profile ID and cart items.
     * @return A {@link RewardsResponse} object containing the effects and other data from Talon.One.
     * @throws org.springframework.web.client.HttpClientErrorException if Talon.One returns a 4xx error.
     * @throws org.springframework.web.client.HttpServerErrorException if Talon.One returns a 5xx error.
     */
    public RewardsResponse evaluateSession(SessionDTO dto) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/v1/sessions")
                .toUriString();

        HttpEntity<SessionDTO> requestEntity = new HttpEntity<>(dto, createAuthHeaders());

        return restTemplate.postForObject(url, requestEntity, RewardsResponse.class);
    }

    /**
     * Confirms a loyalty transaction for a given user, typically after an order is successfully placed.
     * This action finalizes the application of loyalty points (e.g., earning or redemption).
     * This method sends a POST request to the {@code /v1/loyalty/{userId}/confirm} endpoint.
     *
     * @param userId      The unique identifier of the customer profile.
     * @param totalAmount The total amount of the transaction to confirm.
     * @throws org.springframework.web.client.HttpClientErrorException if Talon.One returns a 4xx error.
     * @throws org.springframework.web.client.HttpServerErrorException if Talon.One returns a 5xx error.
     */
    public void confirmLoyalty(String userId, double totalAmount) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/v1/loyalty/{userId}/confirm")
                .buildAndExpand(userId)
                .toUriString();

        // The API likely expects a JSON object, e.g., {"total": 99.99}.
        // We use a Map to create this structure for the request body.
        Map<String, Double> requestBody = Collections.singletonMap("total", totalAmount);
        HttpEntity<Map<String, Double>> requestEntity = new HttpEntity<>(requestBody, createAuthHeaders());

        restTemplate.postForEntity(url, requestEntity, Void.class);
    }

    /**
     * Creates the HttpHeaders required for authenticating with the Talon.One API.
     * This helper method ensures all requests include the necessary API key and content type.
     *
     * @return An HttpHeaders object with the Authorization and Content-Type headers set.
     */
    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        // Talon.One's v1 API uses the "ApiKey-v1" scheme for authentication.
        headers.set("Authorization", "ApiKey-v1 " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}