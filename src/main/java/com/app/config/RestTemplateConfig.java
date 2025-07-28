package com.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * Configures the Spring {@link RestTemplate} for communicating with external APIs.
 * This class centralizes the HTTP client configuration, ensuring that all outgoing
 * requests to the Talon.One API are secure, efficient, and maintainable.
 */
@Configuration
public class RestTemplateConfig {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);

    /**
     * Injects the Talon.One API key from the application's configuration
     * (e.g., application.properties or environment variables). This ensures that
     * sensitive credentials are not hardcoded in the source code.
     */
    @Value("${talonone.api-key}")
    private String talonOneApiKey;

    /**
     * Creates and configures a singleton, thread-safe {@link RestTemplate} bean
     * specifically for the Talon.One client.
     * <p>
     * This RestTemplate is equipped with an interceptor that automatically attaches
     * the necessary authentication headers and logs request details, simplifying the
     * API client logic.
     *
     * @return A configured {@link RestTemplate} instance.
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(talonOneApiInterceptor()));
        return restTemplate;
    }

    /**
     * Creates a {@link ClientHttpRequestInterceptor} for interacting with the Talon.One API.
     * <p>
     * The interceptor performs two main functions:
     * 1. Logs the outgoing request's method and URI for debugging and monitoring purposes.
     * 2. Adds the required 'Authorization' and 'Content-Type' headers for API authentication
     *    and data formatting.
     *
     * @return A {@link ClientHttpRequestInterceptor} instance.
     */
    private ClientHttpRequestInterceptor talonOneApiInterceptor() {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                // Log essential request details at a DEBUG level to avoid excessive logging in production.
                log.debug("Sending request to Talon.One API: {} {}", request.getMethod(), request.getURI());

                // Add the necessary headers for Talon.One API authentication and content type.
                // The "ApiKey-v1" scheme is required by the Talon.One Integration API.
                request.getHeaders().set("Authorization", "ApiKey-v1 " + talonOneApiKey);
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                // Proceed with the request execution.
                return execution.execute(request, body);
            }
        };
    }
}