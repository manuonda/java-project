# Spring Boot Microservices Course

This repository contains the source code for the Spring Boot Microservices Course.

This project is inspired by the work of [SivaLabs](https://github.com/sivaprasadreddy/spring-boot-microservices-course) - thank you, SivaLabs!

## Overview

In this course, we build a BookStore application using Spring Boot, Spring Cloud, and Docker. The application consists of several microservices that work together to provide a seamless experience for managing a bookstore.

## BookStore Microservices Architecture

### Modules

1. **catalog-service**
   - Provides REST API for managing catalog of products (books).
   - **Tech Stack**: Spring Boot, Spring Data JPA, PostgreSQL

2. **order-service**
   - Provides REST API for managing orders and publishes order events to the message broker.
   - **Tech Stack**: Spring Boot, Spring Security OAuth2, Keycloak, Spring Data JPA, PostgreSQL, RabbitMQ

3. **notification-service**
   - Listens to order events and sends notifications to users.
   - **Tech Stack**: Spring Boot, RabbitMQ

4. **api-gateway**
   - API Gateway to internal backend services (catalog-service, order-service).
   - **Tech Stack**: Spring Boot, Spring Cloud Gateway

5. **bookstore-webapp**
   - Customer-facing web application for browsing catalog, placing orders, and viewing order details.
   - **Tech Stack**: Spring Boot, Spring Security OAuth2, Keycloak, Thymeleaf, Alpine.js, Bootstrap

### Learning Objectives

- Building Spring Boot REST APIs
- Database Persistence using Spring Data JPA, PostgreSQL, Flyway
- Event Driven Async Communication using RabbitMQ
- Implementing OAuth2-based Security using Spring Security and Keycloak
- Implementing API Gateway using Spring Cloud Gateway
- Implementing Resiliency using Resilience4j
- Job Scheduling with ShedLock-based distributed Locking
- Using RestClient for Declarative HTTP Interfaces
- Creating Aggregated Swagger Documentation at API Gateway
- Local Development Setup using Docker, Docker Compose, and Testcontainers
- Testing using JUnit 5, RestAssured, Testcontainers, Awaitility, WireMock
- Building Web Application using Thymeleaf, Alpine.js, Bootstrap
- Monitoring & Observability using Grafana, Prometheus, Loki, Tempo (Membership)
- Kubernetes Basics (Membership)
- Deployment to Kubernetes (Membership)

## Local Development Setup

To get started with development:

1. Install Java 21. We recommend using SDKMAN for managing Java versions.
2. Install Docker Desktop.
3. Install IntelliJ IDEA or any of your favorite IDE.
4. Install Postman or any REST Client for API testing.

## Getting Started

To run the application locally, follow these steps:

1. Clone the repository.
2. Navigate to each microservice/module directory (`catalog-service`, `order-service`, etc.).
3. Build each module using Maven or Gradle.
4. Start Docker containers for PostgreSQL, RabbitMQ, etc., if not already running.
5. Start each microservice/module.
6. Access the application through the API Gateway or directly for the web application.

For detailed instructions on running and testing each module, refer to their respective README files.



### Course commands

Create file pom.xml this file is that contain  , to create file run the next command:
```java
mvn wrapper:wrapper
```


- Tools :
  * **[Portainer io](https://docs.portainer.io/v/2.16/start/install/server/docker/linux)** : es una plataforma de administración de contenedores que facilita la gestión de aplicaciones contenedorizadas a través de una interfaz de usuario gráfica intuitiva.. 

  * **[Taskfile.dev](https://taskfile.dev)**: es una herramienta de ejecución de tareas y compilación que busca ser más sencilla y fácil de usar que herramientas como GNU Make. Está escrita en Go, por lo que es un único binario sin dependencias adicionales. Los usuarios describen sus tareas de compilación en un archivo Taskfile.yml utilizando una sintaxis YAML simple.
  Se crea un archivo : **-Taskfile.yml**
  
  Example :
  ```java
    // docker compose up -f deploy
    task start_infra
  ```
  * **[SdkMan](https://sdkman.io/)** 
  Se crea un archivo sdkmanrc para permitir cargar la version requerida de java. Create file **.sdkmanrc**
  ```java
  java=21.0.3-tem
  maven=3.6.3
  ```
   [Tutorial skdman ](https://www.baeldung.com/java-sdkman-intro)


  * **[Create image builder](https://docs.spring.io/spring-boot/maven-plugin/build-image.html#build-image.customization)**:
  To create an image docker, I first need to create the image in `Docker-Hub`. In this case is for catalog-service so, the name of the image in `docker-hub` is: **`manuonda/bookstore-catalog-service`**

  The configuration of the file pom.xml is:
  ```java
  <configuration>
		<image>
		<name>${dockerImageName}</name>
		</image>
  </configuration> 
  ```
  
  The commando to create builder is the next command :
  ```java
   ./mvnw -pl catalog-service spring-boot:build-image -DskipTests
  ```
The command does the following:

- Runs the Maven Wrapper (`mvwn`) to ensure the correct Maven version.
- `-pl catalog-service`: The -pl (short for "project list") option specifies that the command should only be executed on the catalog-service submodule of the project. This is useful if the project has multiple modules and you want to run a command on a specific module.
- Executes the Spring Boot plugin goal `builder-image` to create a container image.
- Skips running tests (`-DskipTests`).


**Note**: Configure docker hub credentials in github for integrate with `github actions`.

