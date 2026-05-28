# Rewards Program (Spring Boot)

## Overview
This project implements a retailer rewards program using Spring Boot.  
Customers earn points based on transaction amounts:
- 2 points per dollar spent over $100
- 1 point per dollar spent between $50 and $100

For example:  
A $120 purchase → (2 × 20) + (1 × 50) = 90 points.

## Features
- REST API endpoint `/api/rewards`
- Calculates monthly and total reward points per customer
- No hardcoded months (uses `YearMonth` dynamically)
- Exception handling for invalid transactions
- Unit and integration tests included
- JavaDocs added for classes and methods

src/main/java/com/example/rewards/
├── RewardsApplication.java
├── controller/RewardsController.java
├── service/RewardsService.java
├── model/Customer.java
├── model/Reward.java
└── exception/InvalidTransactionException.java

## Tech Stack
- Java 17+
- Spring Boot
- JUnit 5 (Jupiter)
- Mockito

## How to Run
```bash
mvn spring-boot:run

How to Test
mvn test

API Endpoint
GET /api/rewards → returns list of rewards per customer