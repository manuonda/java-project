package com.openai.chat;


import org.springframework.ai.chat.ChatClient;
import org.springframework.boot.jdbc.SchemaManagementProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class Simple Prompt
 */


@RestController
@RequestMapping("/api/opeani")
public class SimplePrompt {

    private final ChatClient chatClient;

    public SimplePrompt(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt-base")
    public String getCall(){
        String prompt = """
                Quien fue el ganador del campeon del mundo de futbol 2022;
                """;
        return this.chatClient.call(prompt);
    }

}
