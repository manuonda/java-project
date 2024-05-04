package com.ai.docs.springaidocs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.Resource;
import org.springframework.shell.command.annotation.Command;


/**
 * Class command 
 */
@Command
public class SpringAssistantCommand {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/prompts/spring-boot-reference.st")
    private Resource resource;

    public SpringAssistantCommand( ChatClient chatClient, VectorStore vectorStore){
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @Command(command = "q")
    public String question(@DefaultValue(value="What is a pokemon") String message){
        PromptTemplate promptTemplate = new PromptTemplate(resource);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));
        return chatClient.call(promptTemplate.create(promptParameters))
               .getResult().getOutput().getContent();
    }

    public List<String> findSimilarDocuments(String message){
       List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
       return similarDocuments.stream().map(Document::getContent).toList();
    }
}
