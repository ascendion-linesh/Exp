# Order Service

A Spring Boot microservice for handling order placement in an e-commerce system. Integrates with user-service and rewards-service, persists orders to PostgreSQL, and publishes order events to Kafka.

## Features
- Place orders via REST API (`/orders`)
- Integrates with user-service (user validation)
- Integrates with rewards-service (discount calculation via Talon.One)
- Persists orders in PostgreSQL
- Publishes order events to Kafka (`orders` topic)
- Exception handling and logging
- Swagger/OpenAPI documentation
- Unit and integration tests

## Prerequisites
- Java 17 or higher
- Spring Boot 3.2.x or higher
- PostgreSQL running with a database named `ordersdb`
- Kafka running on `localhost:9092`

## Setup Instructions
1. Clone the repository.
2. Update `src/main/resources/application.yml` with your PostgreSQL and Kafka configuration if needed.
3. Build the project:
   ```
   ./mvnw clean install
   ```
4. Run the application:
   ```
   ./mvnw spring-boot:run
   ```
5. The service will be available at `http://localhost:8083`.

## API Documentation
Swagger/OpenAPI documentation is available at `/swagger-ui.html` (if enabled).

## Database Migration
Flyway migration script is provided as `V1__create_orders_table.sql`.

## Testing
- Unit and integration tests are located under `src/test/java/com/example/orderservice/`
- Run tests with:
  ```
  ./mvnw test
  ```

## Sample API Request
```
POST /orders
Content-Type: application/json
{
  "userId": 12345,
  "cartItems": [
    { "productId": "p1", "quantity": 2 },
    { "productId": "p2", "quantity": 1 }
  ]
}
```

## Security
- Input validation and exception handling included.
- Database access via JPA/Hibernate.

---

**This completes the Order Service setup.**
