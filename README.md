# Product Catalog MVC

A sample CRUD web application built with Java 20, Spring Boot, Spring MVC,
Thymeleaf, Spring Data JPA, and an in-memory H2 database.

## Requirements

- JDK 20 or later
- Maven 3.9+

## Run

```bash
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) to manage products. The H2
database is in memory, so its contents reset when the application stops.

## MVC structure

- `model`: Product entity
- `repository`: persistence access
- `service`: CRUD operations
- `controller`: Spring MVC routes and form handling
- `templates`: Thymeleaf list and edit views