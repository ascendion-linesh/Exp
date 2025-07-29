package com.example.userservice.exception;

public class TalonOneIntegrationException extends RuntimeException {
    public TalonOneIntegrationException(String message) {
        super(message);
    }
    public TalonOneIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
