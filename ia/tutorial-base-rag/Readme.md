### Project Base Pokemon of Spring Boot AI.
This project allow use VectorDatabase Simple , using the technique is know as RAG(Retrieval Agumented Generation). 

The information obtained from the VectorDataBase using Class SimpleVectorDatabase is used to store the text information of the document.

The user enters a message and the RAG technique searches the documents for similar texts. The search result is joined with the message that the user sends to OPEN AI. To do this, a wizard created in the **rag-prompt-template.st** file is used.

The document that is used how base information that find in **docs/pokemon-faq.txt**

The file **data/vectorsore.json** is used to store the information in format vector database (numbers).

To use this application in the **application.yaml** file replace the OpenApi key with your key and model that you use.

```

spring:
 application:
  name: Spring AI RAG
 ai:
  openai:
   api-key: ${OPEN_API_KEY}
   chat:
    options:
     model: gpt-3.5-turbo-0125
```



To execute: 
```
  mvn install
./mvnw spring-boot:run

Example: 
http://localhost:8080/faq?message=What happening in the year 1996?

Output: 
In the year 1996, the Pokémon Red and Green video games were released in Japan, marking the inception of the Pokémon phenomenon.

```



#### Documentation Reference :
https://docs.spring.io/spring-ai/reference/1.0-SNAPSHOT/api/vectordbs.html



