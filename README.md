# Quiz-Application – Microservices with Spring Boot

A microservices-based Quiz Application built using Spring Boot, Eureka, API Gateway, OpenFeign, and PostgreSQL.
This project demonstrates how multiple microservices can work together with service discovery and API routing.

📌 Features

    Question Service 📝
    Stores questions in PostgreSQL

Exposes REST APIs to fetch questions by category/difficulty

Quiz Service 🎲

    Creates quizzes by fetching questions via OpenFeign
    Returns quiz questions to users

Evaluates submitted responses and calculates scores

Eureka Server 🔍

    Handles service discovery & registration
    Keeps track of active microservices

API Gateway 🌐

    Routes all client requests to respective microservices
    Uses Spring Cloud Gateway with Eureka integration

🛠 Tech Stack

    Backend: Spring Boot, Spring Cloud (Eureka, Gateway, OpenFeign)
    Database: PostgreSQL (with Spring Data JPA)

Build Tool: Maven

Language: Java 21

Architecture: Microservices
