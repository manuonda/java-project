#Use the official Openjdk 17 image from Docker Hub
FROM openjdk:21

# set workking directory inside the container 
VOLUME /tmp 
VOLUME /logs

WORKDIR /app
#Copy the compiled java application JAR file into the container 
COPY /target/logging-service.jar /app/app.jar
#Expose the port the Spring Boot Application will run on 
EXPOSE 8081
#Command run the application 
CMD [ "java","-jar", "app.jar" ]


