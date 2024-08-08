# Utilizamos una imagen base con Java 21
FROM openjdk:21-slim
# Establecemos la variable de entorno para el archivo JAR
ARG JAR_FILE=target/*.jar

# Copiamos el archivo JAR al contenedor
COPY ${JAR_FILE} app.jar

# Exponemos el puerto (ajusta si es necesario)
EXPOSE 8081

# Punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]