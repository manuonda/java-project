### Patter Desing Adapter 

The Adapter Design Pattern is a structural design pattern  used to allow two incompatible interfaces to work togheter.
The pattern acts as a bridge between a client and service,
making them compatible without changing their existing code.

### Design 
![pattern_adapter](payment_adapter.png)

### Requeriments 
Java 21


### Testing 
Execute the next command:
```
./mvnw spring-boot:run
```
curl -X POST http://localhost:8080/api/v1/payments?gateway=Stripe \
-H "Content-Type: application/json" \
-d '{
  "amount": 1000,
  "currency": "INR"
}'


### Link 
(Adapter Design Pattern Explained with Spring Boot | Real-Time Example | ‪@Javatechie‬ )[https://www.youtube.com/watch?v=KPnsKMKf3YA&t=18s]