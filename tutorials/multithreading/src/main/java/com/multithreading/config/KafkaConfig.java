package com.multithreading.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {

    @Value("${product.discount.update.topic}")
    private String topicName;

    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServers;
    

    //  @Bean
    // public KafkaAdmin kafkaAdmin() {
    //     Map<String, Object> configs = new HashMap<>();
    //     configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    //     return new KafkaAdmin(configs);
    // }

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(topicName, 3, (short)1);
    }
}
