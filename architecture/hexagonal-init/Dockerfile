FROM bellsoft/liberica-runtime-container:jdk-21-slim-musl

WORKDIR /app

COPY target/*.jar /app/app.jar

EXPOSE  9393

ENTRYPOINT [ "java","-jar","app.jar"]