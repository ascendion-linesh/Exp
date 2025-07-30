# User Service

A fully functional Spring Boot microservice for user management with Talon.One integration.

## Features
- RESTful APIs for user registration, retrieval, update, and deletion
- PostgreSQL persistence with JPA/Hibernate
- Talon.One integration for customer registration
- Centralized exception handling
- DTOs for API communication

## Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL running with a database named `userdb`

## Setup & Running Instructions

1. **Clone the repository:**
   ```
   git clone https://github.com/ascendion-linesh/Exp.git
   cd Exp/user-service
   ```

2. **Configure Database & Talon.One:**
   - Edit `src/main/resources/application.yml` with your PostgreSQL credentials and Talon.One API details.

3. **Build the project:**
   ```
   ./mvnw clean install
   ```

4. **Run the application:**
   ```
   ./mvnw spring-boot:run
   ```
   The service will be available at `http://localhost:8080`.

## API Endpoints

### POST /users
Register a new user.

### GET /users/{id}
Get user by ID.

### GET /users/email/{email}
Get user by email.

### PUT /users/{id}
Update user details.

### DELETE /users/{id}
Delete a user.

## Security Measures
- Input validation in service and controller layers
- Database access secured via JPA
- Centralized exception handling

## Notes
- Use Swagger/OpenAPI for further API documentation if needed.
- Add unit tests for service and repository layers for production readiness.
