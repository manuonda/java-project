#Use the official Openjdk 17 image from Docker Hub
#FROM openjdk:21-jdk-slim
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app
#Copy the compiled java application JAR file into the container 
COPY /target/*.jar /app/app.jar
#Expose the port the Spring Boot Application will run on 
EXPOSE 8081
#Command run the application 
CMD [ "java","-jar", "app.jar" ]
