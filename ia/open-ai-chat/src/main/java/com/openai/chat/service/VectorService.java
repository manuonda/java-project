package com.openai.chat.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VectorService {

    @Value("classpath:/data/pokemon.json")
    Resource pokemonResource;

    VectorStore vectorStore;

    public VectorService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    /**
     * Link documentation :
     * https://docs.spring.io/spring-ai/reference/api/vectordbs.html
     * 
     * @param query
     * @return
     */
    public List<Document> simpleVectorDocument(String query) {
        List<Document> documents = List.of(
                new Document("Spring AI rocks!! Informacion de nuevos datos", Map.of("country", "BG", "year", 2020)),
                new Document("The World Big and Salvation Lurks Around the Corner"),
                new Document("The World Big and Salvation Lucas film"),
                new Document("You walk forward facing the past and you turn back toward the future.",
                        Map.of("country", "NL", "year", 2023)));

        // add documents to the vector store
        vectorStore.add(documents);

        // retrieve documents similar to the given query
        List<Document> results = vectorStore.similaritySearch(SearchRequest.defaults()
                .withQuery(query));
        return results;

    }

    public List<Document> getVectorJson(String query) {
       
            JsonReader jsonReader = new JsonReader(pokemonResource,
                    "nombre",
                    "tipo",
                    "habilidades",
                    "altura",
                    "peso",
                    "shortDescription",
                    "descripcion",
                    "tag"

            );
            List<Document> documents = jsonReader.get();

            vectorStore.add(documents);
            return vectorStore.similaritySearch(
                    SearchRequest.defaults()
                            .withQuery(query));
       
    }

}
