# Delivery Tracker API

[![Build](https://github.com/steffencrespo/delivery-tracker-api/actions/workflows/build.yml/badge.svg)](https://github.com/steffencrespo/delivery-tracker-api/actions/workflows/build.yml)
[![Coverage](https://img.shields.io/badge/coverage-46%25-yellow)](./build/reports/jacoco/test/html/index.html)
![Kotlin](https://img.shields.io/badge/kotlin-1.9-blue?logo=kotlin)

This project is an experiment with a REST API built using **Kotlin**, **Spring Boot**, in-memory **H2 database**, documented via **Swagger UI**, and tested with **JUnit + MockMvc**. It was used as a learning exercise and for integration with AI tools like Cursor and ChatGPT.

---

## How to run

### Using Gradle

```bash
./gradlew bootRun
```

The application will be available at:

```
http://localhost:8080
```

---

### Using Docker

Build the image:

```bash
docker build -t delivery-tracker-api .
```

Run the container:

```bash
docker run -p 8080:8080 delivery-tracker-api
```

---

## Swagger UI

Access the documentation at:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

---

## Example usage with curl

### Get all deliveries

```bash
curl http://localhost:8080/api/deliveries
```

### Get by ID

```bash
curl http://localhost:8080/api/deliveries/1
```

### Get by tracking number

```bash
curl http://localhost:8080/api/deliveries/tracking/TN123456789
```

### Create a new delivery

```bash
curl -X POST http://localhost:8080/api/deliveries \
  -H "Content-Type: application/json" \
  -d '{
        "trackingNumber": "TN999888777",
        "recipientName": "Carlos",
        "recipientAddress": "Rua XPTO, 456",
        "status": "PENDING"
      }'
```

### Update delivery status

```bash
curl -X PUT "http://localhost:8080/api/deliveries/1/status?status=DELIVERED"
```

### Delete a delivery

```bash
curl -X DELETE http://localhost:8080/api/deliveries/1
```

---

## Run tests

```bash
./gradlew test
```

---

## Test coverage (Jacoco)

> To enable Jacoco coverage (to be integrated):

```bash
./gradlew jacocoTestReport
open build/reports/jacoco/test/html/index.html
```

---

## Project structure

```
src
└── main
    └── kotlin
        └── com.deliverytracker
            ├── controller
            ├── model
            ├── repository
            ├── service
            └── DeliveryTrackerApplication.kt
└── test
    └── kotlin
        └── com.deliverytracker
            └── DeliveryControllerTest.kt
```

---

## Author

This repository was created by Leonardo Steffen as part of a learning experiment and integration of AI tools in backend development with Kotlin.

