package com.pattern.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;


@Configuration
@EnableKafka
public class KakfaConfig {


    private final ApplicationProperties applicationProperties;

    public KakfaConfig(ApplicationProperties applicationProperties){
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(this.applicationProperties.orderTopicName()
        ,3,(short)1);
    }

}
