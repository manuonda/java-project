package com.pattern.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pattern")
public record ApplicationProperties(
    String orderTopicName
){

}

