
# Project Aplicacion Blog 
## Description :  Project de Blog de Application

 - Posts Management :  Se realiza Crud(Create, Read, Update and Delete). Pagination
   and Ordenatimiento.
 - Comments Management : CRUD.
 - Authentication and Authorization : Registro, Login and Security.
 - Category Management :  CRUD.


## Tecnologias Utilizadas 
- Java Platform : 17 
- Java Framework: Spring Framework, Spring Security , Spring JPA, FlyWay, DockerCompose.
- Token Base Authentication : JWT
- Database: MySQL
- Swagger

 ## Diagrama de Project.
<br>




## To execute Project, is necesssary docker. 

```
 ./docker compose up
./mvwn clean install -DskipTests
./mvwn spring-boot:run 
```

### To dockerizar application and Run docker
The command docker compose up load the docker mysql to connect 
application database

```
./mvnw clean install -DskipTests
./docker build -t app-blog:1.0 .
./docker compose up
./docker run -p 8085:8085 app-blog:1.0
```
Navigate to [http://localhost:8085/swagger-ui/index.html](http://localhost:8085/swagger-ui/index.html)




https://dev.to/thecodersden/effortless-containerization-deploying-spring-boot-and-mysql-with-docker-and-docker-compose-e8n