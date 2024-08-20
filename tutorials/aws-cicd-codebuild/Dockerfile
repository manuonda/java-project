#use the official jdk
FROM openjdk:21
#working dir
WORKDIR /app
#copy 
COPY ./target/*.jar  /app/app.jar
#expose 
EXPOSE 8081
#command 
CMD ["java","-jar","app.jar"]


