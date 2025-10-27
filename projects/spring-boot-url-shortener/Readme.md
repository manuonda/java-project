### Sistema de Acortador de URLs(URL Shortener System)

Este proyecto es un sistema de acortador de URLs que permite a los usuarios convertir URLs largas en versiones más cortas y manejables. El sistema está diseñado para ser fácil de usar y eficiente, proporcionando una interfaz web simple para la creación y gestión de URLs acortadas.
## Características
- Acortamiento de URLs largas.
- Redirección automática a la URL original al acceder a la URL acortada.
- Interfaz web amigable para usuarios.
- Almacenamiento de URLs acortadas en una base de datos.
- Estadísticas básicas de uso (número de clics).
- Validación de URLs para asegurar que sean correctas antes de acortarlas.
- API REST para integración con otros servicios.
- Historial de URLs acortadas por usuario.
- Sistema de autenticación y autorización para usuarios registrados.

## Tecnologías Utilizadas
- Lenguaje de programación: Java Spring Boot 
- Base de datos: Postgresql 
- Interfaz de usuario: HTML, CSS, JavaScript
- Herramientas de construcción: Maven
- Control de versiones: Git
- Servidor web: Apache Tomcat (integrado con Spring Boot)
- API RESTful
- Validación de entradas con Hibernate Validator


## Instalación
1. Clona este repositorio en tu máquina local.
2. Asegúrate de tener Java y Maven instalados.
3. Configura la base de datos PostgreSQL y actualiza las credenciales en el archivo `application.properties`.
4. Ejecuta el comando `mvn spring-boot:run` para iniciar la aplicación.
## Uso
1. Accede a la interfaz web en `http://localhost:8080`.