FROM bellsoft/liberica-runtime-container:jdk-21-stream-musl as builder

WORKDIR /home/app
ADD . /home/app
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Verificar que el archivo JAR se haya generado
RUN ls -l /home/app/target/

FROM bellsoft/liberica-runtime-container:jre-21-musl

WORKDIR /home/app
EXPOSE 8081

# Verificar que el archivo JAR se esté copiando correctamente
COPY --from=builder /home/app/target/*.jar /app.jar
RUN ls -l /home/app/

ENTRYPOINT ["java", "-jar", "/app.jar"]