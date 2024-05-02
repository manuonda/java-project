package com.tutorial.base.rag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FaqController {
    
    private static final Logger logger = LoggerFactory.getLogger(FaqController.class);

    private ChatClient chatClient;
    private VectorStore vectorStore;

    @Value("classpath:/prompts/rag-prompt-template.st")
    private Resource ragPromptTemplate;


    public FaqController(ChatClient chatClient, VectorStore vectorStore){
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/faq")
    public String faq(@RequestParam(value="message" , defaultValue = "How can I buy tickets for the Olympic Games Paris 2024") String message) {
        logger.info("Faq chat request");
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(2));
        List<String> contentList = similarDocuments.stream().map(Document::getContent).toList();
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("input", message);
        parameters.put("documents", String.join("\n", contentList));
        Prompt prompt = promptTemplate.create(parameters);

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
    
}
