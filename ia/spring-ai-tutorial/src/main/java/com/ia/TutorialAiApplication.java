package com.ia;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;


@SpringBootApplication
public class TutorialAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialAiApplication.class, args);
	}

	private String asText(Resource resource) {
		try (var inputStream = resource.getInputStream()) {
			

			return new String(inputStream.readAllBytes());
		} catch (Exception e) {
			throw new RuntimeException("Failed to read resource as text", e);
		}
	}

	@Value("classpath:devoxx-amsterdam-schedule.md")
	Resource conferenceAgenda;
	
	@Bean
	CommandLineRunner commandLineRunner(ChatClient.Builder chantClienBuilder, WeatherService weatherService){
		 return args -> {
              ChatClient chatClient = chantClienBuilder.build();
			  
			  // System.out.println(chatClient.prompt("Tell me a joke?").call().content());

			  //System instructions && prompt suffing && structured output

			//   record Track(String name, List<Track> talks){
			// 	record Talk(String time, String session, String location, String track){}
			//   }

			//   List<Track> talks = chatClient.prompt()
			//   .system("You are a useful assistant. Be polite, and always finish the sentence with 'May the force be with you.'")
			//   .user( u -> u.text("Get the list of talks grouped by tracks:\n {additionalContext}")
			//   .param("additionalContext", asText(conferenceAgenda)))
			//   .call()
			//   .entity(new ParameterizedTypeReference<List<Track>>() {});
			  
            //    System.out.println(talks);
			//    System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(talks));
			  

			// CHAT MEMORY 
			// var chatClient2 = chantClienBuilder
			// .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
			// .build();

			// System.out.println("Introducint the name: " + chatClient2.prompt("My name is David Garcia").call().content());
			// System.out.println("Whats si my name : "+ chatClient2.prompt("What is my name").call().content());

             
			//Tools
			var output = chatClient.prompt()
			.tools(weatherService)
			.user("Cual es el timepo en Oregon Estados UNidos")
			.call()
			.content();
			System.out.println("outpu tools : " + output);


		 };
	}

}
