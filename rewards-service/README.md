# rewards-service

The `rewards-service` is a Spring Boot 3.2.x+ microservice (Java 17+) for the e-commerce platform. It evaluates discounts and manages customer rewards by integrating with the Talon.One API. The service exposes a REST endpoint for cart evaluation, listens to Kafka order events to confirm loyalty actions, and ensures robust error handling, logging, and configuration.

## Features
- Integrates with Talon.One for campaign and loyalty management
- Exposes `POST /rewards/evaluate` endpoint for cart session evaluation
- Listens to Kafka order events and processes loyalty actions idempotently
- Robust error handling, logging, and configuration via `application.yml`
- OpenAPI/Swagger documentation
- Unit and integration tests

## Folder Structure
```
rewards-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/rewards/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── integration/
│   │   │   │   └── talonone/
│   │   │   ├── kafka/
│   │   │   ├── model/
│   │   │   ├── service/
│   │   │   └── RewardsServiceApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── logback-spring.xml
│   └── test/
│       ├── java/com/example/rewards/
│       │   ├── controller/
│       │   ├── integration/
│       │   ├── kafka/
│       │   └── service/
│       └── resources/
│           └── sample/
│               ├── sample-cart-evaluate-request.json
│               ├── sample-cart-evaluate-response.json
│               └── sample-kafka-order-event.json
├── pom.xml
└── README.md
```

## References
- [user-service](../user-service)
- [order-service](../order-service)

## Getting Started
See the documentation and code for details on configuration, endpoints, and integration.
