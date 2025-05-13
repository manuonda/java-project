Jamilton
https://github.com/JamiltonQuintero/NotModularHexagonalArquitecture/tree/master/src/main/java/com/jamiltonquintero/hexagonal
## Hexagonal Architecture Overview

Hexagonal Architecture, also known as Ports and Adapters, is a design pattern that aims to create loosely coupled application components. This architecture divides the application into three main layers:

### 1. Domain
The **Domain** layer contains the core business logic and rules. It is independent of any external systems or frameworks. This layer represents the heart of the application.

### 2. Application
The **Application** layer acts as a mediator between the Domain and Infrastructure layers. It handles application-specific logic, such as orchestrating use cases and managing workflows.

### 3. Infrastructure
The **Infrastructure** layer contains the implementation details for external systems, such as databases, APIs, and user interfaces. It interacts with the Domain and Application layers through well-defined interfaces.

### Architecture Diagram

Below is a simplified representation of the Hexagonal Architecture:

```
          +-------------------+
          |   Infrastructure  |
          +-------------------+
                   ^   |
                   |   v
          +-------------------+
          |    Application    |
          +-------------------+
                   ^   |
                   |   v
          +-------------------+
          |       Domain      |
          +-------------------+
```

### Repository Reference

For more details and the implementation of this architecture, visit the following repository:

[Jamilton's Hexagonal Architecture Example](https://github.com/JamiltonQuintero/NotModularHexagonalArquitecture/tree/master/src/main/java/com/jamiltonquintero/hexagonal)