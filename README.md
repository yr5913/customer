# HealthPro Customer Microservice

The HealthPro Customer Microservice is responsible for managing customer information within the HealthPro hospital
software system. This microservice provides HTTP endpoints for adding, modifying, updating, and deleting customer
records in the HealthPro database.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Configuration](#configuration)
- [Running the Microservice](#running-the-microservice)
- [Testing](#testing)
- [Deployment](#deployment)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 11 or later
- MySQL Database
- [Spring Boot](https://spring.io/projects/spring-boot) framework
- Maven for building the project

## Getting Started

Follow these steps to set up and run the Customer Microservice:

1. Clone this repository:

   ```shell
   git clone https://github.com/your-username/healthpro-customer-microservice.git
   cd healthpro-customer-microservice
2. Configure your MySQL database settings in application.properties.
3. Build the microservice:
   ```shell
   mvn clean install

5. Run the microservice:
   ```shell
   java -jar target/healthpro-customer-microservice.jar

## API Endpoints

`POST /api/customer/add`: Create a new customer.

`GET /api/customer/get/{id}`: Retrieve customer information by ID.

`PUT /api/customer/delete/{id}`: Update customer information.

`DELETE /api/customer/update/{id}`: Delete a customer.

## Database Schema

The microservice uses the database schema in the below directory to store customer information:
`src/main/resources/com/schema.sql`

## Configuration
You can configure the microservice by modifying the `application.properties` file. Specify database connection details,
server port, and other settings there.

## Running the Microservice

1. To run the microservice locally, use the following command:
   ```shell
   java -jar target/healthpro-customer-microservice.jar
2. The microservice will start on port 8080 by default. You can access it in your web browser at `http://localhost:8080`



## Testing

1. Unit tests and integration tests are available. Run tests using the following command:
   ```shell
   mvn test
2. For API testing, you can use tools like Postman or curl to interact with the endpoints.







