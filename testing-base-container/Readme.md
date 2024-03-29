## Project Demo de Testing Unitario , De Integracion. Test Containers basandome en un Dominio de clase Usuario.


### - Project de Test de las capas siguientes: Layer Repository, Service, Controller. Sobre la Clase Usuario 
### - Indicando test Unitarios y Test de Integration.
### - Ademas de Utilizar TestContainers

## Documents Resources 
- [Documento Layer Repositorio](https://github.com/manuonda/java-project/blob/main/testing-base-container/Spring%20Boot%20Testing%20-JPA%20Repository.docx)
- [Documento Layer Service](https://github.com/manuonda/java-project/blob/main/testing-base-container/Spring%20Boot%20Testing-%20Service%20Layer.docx)
- [Documento Test Integration](https://github.com/manuonda/java-project/blob/main/testing-base-container/Integration%20Test.docx)

## Diagrama de Imagen de Testing Layers Repository, Controller,
 
![Vista previa de la imagen](https://raw.githubusercontent.com/manuonda/java-project/main/testing-base-container/diagrama_layer.png)




## Diagrama de Imagen Test Integration.

![Vista previa de la imagen](https://raw.githubusercontent.com/manuonda/java-project/main/testing-base-container/test_integration.png)




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
cd testing-base-container

# Instala las dependencias
mvn clean install

# Ejecuta las pruebas unitarias
mvn test
