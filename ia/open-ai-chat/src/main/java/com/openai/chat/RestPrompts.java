package com.openai.chat;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/openai")
@RestController
public class RestPrompts {
    
    private final ChatClient chatClient;

    public RestPrompts(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt-simple")
    public String getPrompt(){
        String texto ="""
                Cual es el jugador con mas balones de oro
                en el futbol
                """;

                return chatClient.call(texto);
    }


    /**
     * Funcion que permite establecer
     * una configuration que modelo y que temperatura
     * cargar
     * @return
     */
    @GetMapping("/prompt-simple-configuration")
    public String getPromptSimpleConfiguration(){
        String texto = """
                Cual es el jugador con mas balones de oro 
                en el futbol
                """;

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withModel("gpt-4-32k")
                .withTemperature(0.4f)
                .build();
        Prompt prompt = new Prompt(texto, options);

        return  chatClient.call(prompt).toString();

    }


    /**
     * Funcion prompt template
     * @param jugador
     * @return
     */
    @GetMapping("/prompt-template/{jugador}")
    public String getPromptParameter(@PathVariable("jugador")String jugador){
        String texto = """
                 Cuantos balones de oro tiene el {jugador}?
                """;
        PromptTemplate template = new PromptTemplate(texto);
        template.add("jugador",jugador);
        return chatClient.call(template.render());
    }


    /**
     * Parseo de salida en una clase
     * 
     * @param actor
     * @return
     */
    @GetMapping("/prompt-parse/{actor}")
    public ActorFilms getPromptParse(@PathVariable(value  = "actor") String actor){
        var outputParser =  new BeanOutputParser<ActorFilms>(ActorFilms.class);
        String message = """
                Generate the filmography for the actor {actor}
                {format}
                """;
        PromptTemplate template = new PromptTemplate(message);
        template.add("actor", actor);
        template.add("format", outputParser.getFormat());
        Prompt prompt = template.create();
        Generation generation = chatClient.call(prompt).getResult();

        ActorFilms actorFilms = outputParser.parse(generation.getOutput().getContent());
        return actorFilms;
    }





}
