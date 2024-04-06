package com.openai.chat.controller;


import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/")
public class RestEmbedding {

    private final EmbeddingClient embeddingClient;
    
    public RestEmbedding(EmbeddingClient embeddingClient){
          this.embeddingClient = embeddingClient;

    }
}
