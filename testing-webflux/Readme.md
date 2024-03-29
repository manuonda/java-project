
## PROJECT Testing Spring Webflux , Mongo
##

### - Project de Test de las capas siguientes: Layer Repository, Service, Controller. Sobre la Clase Empleado
### - Indicando test Unitarios y Test de Integration.
### - Ademas de Utilizar TestContainers

## Documents Resources
[Document Spring Webflux]()

## Diagrama de Layers.


## Basandome en el curso de Udemy siguiente: https://www.udemy.com/course/testing-spring-boot-application-with-junit-and-mockito/

## Instrucciones de instalación y ejecución

Sigue estos pasos para ejecutar el proyecto en tu máquina local:

## Requeriments:
* Es necesario [Docker](https://docs.docker.com/engine/install/)
* La version minima de Java es 17

```bash
# Clona el repositorio desde GitHub
git clone https://github.com/manuonda/java-project.git

# Navega al directorio del proyecto
cd testing-webflux

# Instala las dependencias
mvn clean install

# Ejecuta las pruebas unitarias
mvn test





Para probar los mediante Postman se puede correr un contenedor 
de mongo db.

* docker run -d --name  mongodb -p 27017:27017 mongo
* docker exec -it mongodb bash 


### Comandos mondogb 
* show dbs <br>
* use db_test <br>
* db.empleados.find() <br>

