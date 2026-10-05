# TaskEntity-Service Application

A RESTful production-ready Task management backend application built using **Java**, **Spring MVC**, **Spring Data JPA**, and **MariaDB**.This application provides full CRUD functionality, custom data transfer objects (DTOs), request payload validations, centralized global exception handling, data masking, and persistence using Spring Data JPA.

---

## Features

- **Full Lifecycle CRUD**: Complete RESTful endpoints to create, retrieve, update, and delete tasks.
- **Persistence**: Relational database abstraction backed by MariaDB, utilizing custom query methods.
- **RESTful Status Codes**: Accurate HTTP status responses (201 Created for additions, 204 No Content for deletions, 404 Not Found for missing resources).
- **Automated Payload Validation**: Enforces strict field constraints (such as non-blank task names and valid date formats) using @Valid at the controller layer.
- **Centralized Exception Handling**: @RestControllerAdvice intercepts uncaught runtime errors (ResourceNotFoundException, MethodArgumentNotValidException) globally.

---

## Tech Stack

- **Language:** Java 17+
- **Framework:** Spring Boot (Spring MVC, Spring Data JPA, Spring Validation)
- **Database:** MariaDB / MySQL
- **Build Tool:** Maven
- **API Testing:** Postman

---

## Prerequisites

Before running this application, ensure you have the following installed:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/) or higher
- [Maven](https://maven.apache.org/)
- [MariaDB Server](https://mariadb.org/)
- [Postman](https://www.postman.com/)

---


