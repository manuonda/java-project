package com.openai.chat.controller;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.openai.chat.service.VectorService;

@RestController
@RequestMapping("/api/v1/vector")
public class VectorController {
    
    private VectorService vectorService;

    public VectorController(VectorService vectorService){
        this.vectorService = vectorService;
    }

    @GetMapping("/generate/document/{query}")
    public List<Document> getDocumentQuery(@PathVariable("query") String text){
        return vectorService.simpleVectorDocument(text);
    }

    @GetMapping("/generate/json/{query}")
    public String getJsonQuery(@PathVariable("query") String query){
        return vectorService.getVectorJson(query).get(0).getContent().toString();
    }
}
