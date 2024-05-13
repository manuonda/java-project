# Use an official Maven image as the base image
#FROM maven:3.8.4-openjdk-11-slim AS build

#Set the working directory in the container
# WORKDIR /app

# # Copy the pom.xml and the project files to the container
# COPY src /src
# COPY pom.xml .
# COPY mvnw .
# # Build the application using Maven
# RUN mvn clean package install -DskipTests

# Run stage
## Use an official OpenJDK image as the base image
FROM eclipse-temurin:17
LABEL mentainer="manuonda@gmail.com"

WORKDIR /app
COPY target/*.jar /app/app.jar
ENV EXPOSE 8090
USER 10014
ENTRYPOINT ["java","-jar","app.jar"]