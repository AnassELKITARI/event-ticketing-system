# EventTix - Event Management System

Spring Boot-powered app designed to handle event scheduling and ticket sales. It features a dual-role security system and automated data generation.

## Features
* **Role-Based Access Control (RBAC):** Distinct workflows for `ADMIN` (create/manage events) and `USER` (secure booking).
* **Automated Ticket Factory:** Logic-driven ticket generation based on event capacity during creation.
* **RESTful API:** Clean API endpoints for ticket state transitions (Book/Release).
* **Security Redirection:** Intelligent guest handling that redirects unauthorized actions to a secure login.

## Tech Stack
* **Backend:** Java 21, Spring Boot 3.4
* **Security:** Spring Security 6
* **Persistence:** Spring Data JPA, MySQL
* **Frontend:** Thymeleaf, Bootstrap 5, JavaScript

## Getting Started
1. Clone the repository: `git clone https://github.com/YOUR_USERNAME/event-ticketing-system.git`
2. Run the application: `mvn spring-boot:run`
3. Access at: `http://localhost:8080/events`

### **Default Credentials**
| Role | Username | Password |
| :--- | :--- | :--- |
| **Administrator** | `admin` | `admin123` |
| **Standard User** | `customer` | `pass123` |
