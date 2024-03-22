
## PROJECT Testing Spring Webflux , Mongo
##

* docker run -d --name  mongodb -p 27017:27017 mongo
* docker exec -it mongodb bash 


### Comandos mondogb 
* show dbs <br>
* use db_test <br>
* db.empleados.find() <br>



version: '3.1'

services:
mongodb:
image: mongo
container_name: mongodb
restart: always
ports:
- "27017:27017"
volumes:
- mongodb_data:/data/db
environment:
- MONGO_INITDB_ROOT_USERNAME=admin
- MONGO_INITDB_ROOT_PASSWORD=password

volumes:
mongodb_data:
driver: local

