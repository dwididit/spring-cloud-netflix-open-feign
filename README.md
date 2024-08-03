# Spring Cloud Netflix and Open Feign

This project contains a sample microservices architecture using Spring Cloud Netflix and Open Feign. The project consists of three microservices: `UserService`, `ProductService`, and `TransactionService`. These services communicate with each other using Feign clients and are registered with Eureka for service discovery.

## Table of Contents

- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Services](#services)
    - [Eureka Server](#eureka-server)
    - [UserService](#userservice)
    - [ProductService](#productservice)
    - [TransactionService](#transactionservice)
- [APIs](#apis)
    - [UserService APIs](#userservice-apis)
    - [ProductService APIs](#productservice-apis)
    - [TransactionService APIs](#transactionservice-apis)
- [Logging](#logging)
- [Troubleshooting](#troubleshooting)

## Project Structure
```markdown
Spring Cloud Netflix and Open Feign/
├── eureka-server/
├── user-service/
├── product-service/
└── transaction-service/
```


## Getting Started

### Prerequisites

- Java 21
- Maven
- PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    gh repo clone dwididit/spring-cloud-netflix-open-feign
    ```

2. Navigate to the project directory:
    ```sh
    cd spring-cloud-netflix-open-feign
    ```

3. Set up the PostgreSQL database for each service:
    ```sh
    CREATE DATABASE user_service;
    CREATE DATABASE product_service;
    CREATE DATABASE transaction_service;
    ```

4. Update the database configurations in `application.properties` for each service:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/{database_name}
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    ```

## Services

### Eureka Server

This service acts as a service registry where all other services register themselves.

#### Running the Eureka Server

1. Navigate to the `eureka-server` directory:
    ```sh
    cd eureka-server
    ```

2. Run the Eureka Server:
    ```sh
    mvn spring-boot:run
    ```

### UserService

This service handles user-related operations.

#### Running the UserService

1. Navigate to the `user-service` directory:
    ```sh
    cd user-service
    ```

2. Run the UserService:
    ```sh
    mvn spring-boot:run
    ```

### ProductService

This service handles product-related operations.

#### Running the ProductService

1. Navigate to the `product-service` directory:
    ```sh
    cd product-service
    ```

2. Run the ProductService:
    ```sh
    mvn spring-boot:run
    ```

### TransactionService

This service handles transactions between users and products and communicates with both `UserService` and `ProductService`.

#### Running the TransactionService

1. Navigate to the `transaction-service` directory:
    ```sh
    cd transaction-service
    ```

2. Run the TransactionService:
    ```sh
    mvn spring-boot:run
    ```

## APIs

### UserService APIs

- **Register User**
    ```http
    POST /user/register
    ```
    ```json
    {
        "username": "string",
        "email": "string"
    }
    ```

- **Get All Users**
    ```http
    GET /user/all
    ```

- **Get User by ID**
    ```http
    GET /user/{id}
    ```

### ProductService APIs

- **Get Product by ID**
    ```http
    GET /products/{id}
    ```

### TransactionService APIs

- **Create Transaction**
    ```http
    POST /transaction/create
    ```
    ```json
    {
        "userId": 1,
        "productId": 1
    }
    ```

- **Get All Transactions**
    ```http
    GET /transaction/all
    ```

- **Get Transaction by ID**
    ```http
    GET /transaction/{id}
    ```

- **Get Transactions by User ID**
    ```http
    GET /transaction/user/{userId}
    ```

## Logging

Logging is enabled for Feign clients and Eureka for detailed tracing.

- Feign Client Logging
    ```properties
    logging.level.org.springframework.cloud.openfeign=DEBUG
    logging.level.dev.dwidi=DEBUG
    ```

- Eureka Logging
    ```properties
    logging.level.org.springframework.cloud.netflix.eureka=DEBUG
    ```

## Troubleshooting

If you encounter issues, check the following:

1. Ensure all services are running and registered with Eureka.
2. Verify the database configurations in `application.properties`.
3. Check the logs for any errors or issues.
4. Ensure Feign clients are correctly configured and pointing to the right service names and paths.

For any other issues, please raise an issue on the GitHub repository.