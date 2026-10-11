# Bookstore Management API 📚

A robust, stateless RESTful backend service for managing a bookstore, built with Spring Boot 3 and PostgreSQL. This project features secure JWT-based authentication, Role-Based Access Control (RBAC), and interactive API documentation.

## Tech Stack
* **Language:** Java
* **Framework:** Spring Boot 3 (Spring Web, Spring Security, Spring Data JPA)
* **Database:** PostgreSQL (Containerized via Docker)
* **Security:** JSON Web Tokens (JJWT 0.12.x), BCrypt Password Hashing
* **API Documentation:** Swagger UI (springdoc-openapi)

## Key Features
* **Authentication & Authorization:** Secure user registration and login endpoints that issue stateless JWTs.
* **Role-Based Access Control (RBAC):** Distinct permissions for `USER` and `ADMIN` (Librarian) roles.
* **Book Requests:** Users can submit requests to borrow books, which are stored as pending in the database.
* **Admin Dashboard API:** Librarians can view all pending requests, approve/reject book requests, and manage registered users.
* **Interactive UI:** Fully integrated Swagger UI to test protected endpoints directly in the browser.

## What I Learned Building This

As a computer science engineering student expanding my backend development skill set, building this API provided deep, hands-on experience with enterprise-level Java concepts and modern security practices:

* **Modern Spring Security (v6+):** I learned how to configure the `SecurityFilterChain` without deprecated WebSecurityConfigurerAdapters, disabling CSRF for stateless APIs, and setting session creation policies to `STATELESS`.
* **Stateless JWT Implementation:** I implemented token generation, parsing, and validation using the modern JJWT 0.12.x library. This included working with `SecretKey` generation and extracting cryptographic payloads to authenticate users on every request without relying on server memory.
* **Custom Security Filters:** I built a custom `JwtAuthenticationFilter` that intercepts incoming HTTP requests, validates the Authorization header, and securely injects the user's identity into the Spring Security Context before the request hits the controllers.
* **Role-Based Routing:** I learned how to apply authorization rules at the route level using `.hasAuthority("ADMIN")`, ensuring that standard users receive a `403 Forbidden` error if they attempt to access librarian-only endpoints.
* **Entity Mapping & ORM Debugging:** I gained experience mapping Java models to database tables using Hibernate/JPA. Crucially, I learned how to debug and resolve database constraint errors (like `NOT NULL` violations) by managing Docker container lifecycles and dropping outdated schemas.
* **API Documentation:** I integrated `springdoc-openapi` and configured global security schemes so that the Swagger UI automatically generates a bearer-token authorization modal, making testing drastically easier than manually passing headers in Postman.

## Local Setup Instructions

**1. Start the Database**
Ensure Docker is installed and running, then spin up the PostgreSQL container:
```bash
docker compose up -dBook