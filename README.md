# TaskEntity-Service Application

A RESTful Task management backend application built using **Java**, **Spring MVC**, **Spring Data JPA**, and **MariaDB**. The application provides complete **CRUD** (Create, Read, Update, Delete) functionality for task management and is fully tested using **Postman**.

---

## Features

- **Create**: Add new tasks with title, description, and status.
- **Read**: Fetch a list of all tasks or query a specific task by ID.
- **Update**: Modify task details or update completion status.
- **Delete**: Remove tasks from the database by ID.
- **Persistence**: Relational data storage backed by MariaDB using Spring Data JPA / Hibernate.

---

## Tech Stack

- **Language:** Java 17+
- **Framework:** Spring Boot (Spring MVC, Spring Data JPA)
- **Database:** MariaDB
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

## Getting Started

### 1. Database Setup

Open your MariaDB terminal or GUI tool (e.g., DBeaver, HeidiSQL) and create a database:

```sql
CREATE DATABASE todo_db;