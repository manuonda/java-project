#use the official jdk
FROM openjdk:21
#working dir
WORKDIR /app
#copy 
COPY ./target/course-service.jar  /app
#expose 
EXPOSE 8081
#command 
CMD ["java","-jar","course-service.jar"]


