
## Outbox Pattern 

The ```Outbox Pattern``` is a design pattern used to ensure data consistency between microservices or between a microservice and external system. It helps to handle the problem of ensuring that messagees are reliably sent when a transaction is committed.

#### Key Concepts:
*  Outbux Table: A dedicated table where events or messages are stored as part of the same transaction that modifies the business data
*  Transaction: When a business operation is performed, the event is stored in the outbox table within the same transaction.
*  Message Relay: A separate process reads the outbox table and sends the messages to the message broker or external system.
  

### Links  
[Transactional outbox, manejo de eventos transaccionales](https://www.youtube.com/watch?v=vO3WbkmBUaQ)



#### Patterns to reade from table outobx

 Transactional log tailing
Transactional log tailing is a technique used to capture and process changes made to a database's transaction log in real-time. It involves continuously monitoring the transaction log for any new entries and extracting relevant information, such as data modifications or events, to be processed by other systems or microservices. This pattern is commonly used in scenarios where data consistency and synchronization between different components or systems is crucial. By tailing the transaction log, changes can be reliably captured and propagated to ensure data integrity and maintain a consistent state across the system.
#### Pulling publisher