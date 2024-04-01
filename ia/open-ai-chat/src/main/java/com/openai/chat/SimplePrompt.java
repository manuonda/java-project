package com.openai.chat;

import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/openai")
public class SimplePrompt {
    
    private final ChatClient chatClient;

    public SimplePrompt(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt")
    public String getPrompt(){
        String texto ="""
                Cual es el jugador con mas balones de oro
                en el futbol
                """;

                return chatClient.call(texto);
    }

}
