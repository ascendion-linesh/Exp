package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * DTO representing a customer session to be evaluated by Talon.One.
 * This is the data structure sent to the Talon.One Integration API's
 * session evaluation endpoint.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {

    /**
     * The unique identifier of the customer profile in Talon.One.
     */
    private String profileId;

    /**
     * The state of the session (e.g., "open", "closed").
     * "open" is used for evaluation without finalizing.
     */
    private String state = "open";

    /**
     * A map of attributes relevant to the current session, such as cart items.
     */
    private Map<String, Object> attributes;
}