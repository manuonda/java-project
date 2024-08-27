
## Outbox Pattern 

The ```Outbox Pattern``` is a design pattern used to ensure data consistency between microservices or between a microservice and external system. It helps to handle the problem of ensuring that messagees are reliably sent when a transaction is committed.

#### Key Concepts:
*  Outbux Table: A dedicated table where events or messages are stored as part of the same transaction that modifies the business data
*  Transaction: When a business operation is performed, the event is stored in the outbox table within the same transaction.
*  Message Relay: A separate process reads the outbox table and sends the messages to the message broker or external system.
  
### Project Outbox Pattern 
![outbox_pattern](images/outbox_pattern.png)


In this case the Service **Order Service** write in the tables ```order_tbl``` and write in the table ```outbox_tbl```. The ```outbox_tbl``` table  contains the information that will be used to send to other services . The Service **Order Poller Service** obtanins the information from  table of  ```outbox_tbl``` table of the registered ones that do not process(state in false) and Produces the messages to be  sent to Apache Kafka.


### Technologies 

- Java 21
- Spring Boot 3 
- Docker 
- Kubernetes
- Apache Kakfa 


#### Run Standalone application
**<u>Director infra(kafka, zookeper, database)</u>**

```
outbox-pattern/deployment$ docker compose -f infra.yml up
```
**<u>Order service</u>**
```
outbox-pattern/order-service$ docker build -t order-service .
outobx-pattern/order-service$ docker run -p 8081:8081 \
  --network microservices \
  -e DB_URL=order-db \
  -e DB_PORT=5432 \
  -e DB_NAME=postgres \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=postgres \
  order-service

URL : http://localhost:8081/swagger-ui/index.html
``` 

 **<u>Order Poller</u>**
 ```
 outbox-pattern/order-poller$ docker build -t order-poller .
 outbox-pattern/order-poller$ docker run -p 8082:8082 \
  --network microservices \
  -e DB_URL=order-db \
  -e DB_PORT=5432 \
  -e DB_NAME=postgres \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=postgres \
  -e KAFKA_URL=kafka \
  -e KAFKA_PORT=19092 \
  order-poller

  URL application: http://localhost:8086/ui/clusters/local/brokers/1
 ```


#### Run Application using docker compose from Dockerfile 


In the directory **deployment** run the next command to execute docker-compose : 
```
outbox-pattern/deployment$ docker compose -f infra.yml -f apps.yml up 
[+] Running 6/0
 ✔ Container kafka-ui       Created                                                                                                                                  0.0s 
 ✔ Container zoo            Created                                                                                                                                  0.0s 
 ✔ Container kafka          Created                                                                                                                                  0.0s 
 ✔ Container order-db       Created                                                                                                                                  0.0s 
 ✔ Container order-service  Created                                                                                                                                  0.0s 
 ✔ Container order-poller   Created                   ```
```


In the navigator OpenAPI:
[http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

![swagger_ui](images/localhost8081-swagger.png)

Apache Kafka UI:
![apache_kafkaui](images/localhost_kafka.png)


### Links  
[Transactional outbox, manejo de eventos transaccionales](https://www.youtube.com/watch?v=vO3WbkmBUaQ)
[Outbox Pattern in Spring Boot 3 and Apache Kafka ](https://dev.to/axeldlv/springkafka-outbox-pattern-in-spring-boot-3-and-apache-kafka-2o3o)
[Integración de Apache Kafka con Spring Boot](https://www.machinet.net/tutorial-es/integrating-apache-kafka-with-spring-boot)
