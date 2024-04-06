package com.openai.chat.config;


import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuracion de la clase Vector
 */
@Configuration
public class VectorStoreConfig {


    @Bean
    public VectorStore vectorStore(EmbeddingClient embeddingClient){
        return new SimpleVectorStore(embeddingClient);
    }

}
