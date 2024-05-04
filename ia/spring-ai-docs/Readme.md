### Spring Boot Reference Docummentation Assitant with Spring AI & ChatpGPT4

Spring Boot AI is used in this project, which uses the ETL technique(1) In which use teh document **pokemons.pdf** and embedding in a stored PostgresqlVector database(2). Use the RAG(3) technique to find information in this document, "similar" information to find, and send to the Chat OpenAI . This application use command shell.

To execute:
```
mvn clean install 
./mvnw spring-boot:run 

To input message: 
shell:>q "Who is Ash in pokemon?"
Ash is a character in Pokemon who is known for his determination and never giving up attitude. He was late to receive his first Pokémon in Professor Oak's laboratory, but he insisted until he was finally given Pikachu, the most desired Pokémon. Ash is also known for keeping control of his temperament and determination. He is portrayed as an independent young man in the series.
shell:>

```

Documentation : 

- ETL Pipeline : https://docs.spring.io/spring-ai/reference/api/etl-pipeline.html
- Postgresql Vector Database : https://docs.spring.io/spring-ai/reference/api/vectordbs/pgvector.html
- RAG: https://docs.spring.io/spring-ai/reference/concepts.html#concept-rag
