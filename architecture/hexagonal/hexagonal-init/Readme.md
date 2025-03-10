# Hexagonal Architecture Project

This project demonstrates the implementation of a Hexagonal Architecture in a Java application using Spring Boot.

## Project Structure
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── hexagonal/
│   │           ├── application/
│   │           │   └── usecase/
│   │           │       ├── IProductFindInteractor.java
│   │           │       └── ProductFindInteractor.java
│   │           ├── common/
│   │           │   ├── PriceFormatter.java
│   │           │   └── ProductDto.java
│   │           ├── domain/
│   │           │   ├── model/
│   │           │   │   └── Product.java
│   │           │   └── ports/
│   │           │       └── ProductRepositoryPort.java
│   │           ├── infrastructure/
│   │           │   └── db/
│   │           │       ├── adapter/
│   │           │       │   └── ProductRepositoryAdapter.java
│   │           │       ├── controller/
│   │           │       │   └── ProductController.java
│   │           │       ├── entity/
│   │           │       │   └── ProductEntity.java
│   │           │       └── mapper/
│   │           │           └── ProductMapper.java
│   │           └── HexagonalApplication.java
│   └── resources/
│       ├── application.properties
│       └── db/
│           └── migration/
│               └── V1.0.0__create_table_insert_rows.sql
└── test/
    └── java/
        └── com/
            └── hexagonal/
                └── HexagonalApplicationTests.java
```

## Hexagonal Architecture

Hexagonal Architecture, also known as Ports and Adapters, is an architectural pattern used to create loosely coupled application components that can be easily connected to their software environment. The main goal is to isolate the core logic of the application from the external systems and frameworks.

### Layers

1. **Domain**: This layer contains the business logic and domain entities. It is the core of the application and should not depend on any other layers.
2. **Application**: This layer contains the use cases and application services. It orchestrates the flow of data between the domain and the external systems.
3. **Infrastructure**: This layer contains the implementation details of the external systems, such as databases, message brokers, and web frameworks.

- **Primary Adapters**: These are the entry points to the application, such as HTTP controllers, CLI commands, or API endpoints.
- **Secondary Adapters**: These are the external systems that the application interacts with, such as databases, message brokers, or mail services.

### Diagram

![Hexagonal Architecture](./images/diagrama.png)


### Source Code Explication

#### Controller Layer

- **ProductController.java**: This class is a primary adapter that handles HTTP requests. It has a method `findProductByIdProduct` which receives a product ID and delegates the request to the application layer.

#### Application Layer

- **ProductFindInteractor.java**: This class implements the `IProductFindInteractor` interface and contains the business logic for finding a product by its ID. It interacts with the domain layer to retrieve the product data.

#### Domain Layer

- **Product.java**: This class represents the product entity in the domain layer. It contains the core business logic and attributes of a product.
- **ProductRepositoryPort.java**: This interface defines the contract for the repository. It is implemented by the infrastructure layer to interact with the database.

#### Infrastructure Layer

- **ProductRepositoryAdapter.java**: This class is a secondary adapter that implements the `ProductRepositoryPort` interface. It uses `ProductMapper` to convert between `ProductEntity` and `Product` and interacts with the database through `ProductEntity`.
- **ProductEntity.java**: This class represents the product entity in the database.
- **ProductMapper.java**: This class is responsible for mapping between `Product` and `ProductEntity`.

The `findProductByIdProduct` method flow:
1. `ProductController` receives the HTTP request.
2. `ProductFindInteractor` processes the request and interacts with the domain layer.
3. `ProductRepositoryAdapter` retrieves the product data from the database.
4. `ProductMapper` converts the data between `ProductEntity` and `Product`.
5. The response is sent back to the `ProductController` and then to the client.


## Getting Started

To run the application, use the following commands:

```sh
# Build the project
./mvnw clean install -D skipTests

# Run the application
./mvnw spring-boot:run
```



To run the application with Docker:

```sh
# Build the Docker image
docker build -t hexagonal-app .

# Run the Docker container
docker-compose up
```

## Example Requests
```java
curl -X GET "http://localhost:8081/api/v1/products/camiseta-1234"
{"productId":"camiseta-1234","name":"Camiseta Blanca","description":"Description 1","price":"10.0 €"}(base)
```

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.