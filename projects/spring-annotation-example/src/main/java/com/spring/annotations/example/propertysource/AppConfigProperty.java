package com.spring.annotations.example.propertysource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
// @PropertySource("classpath:mail.properties")
// @PropertySource("classpath:messages.properties")

@PropertySources({
    @PropertySource("classpath:mail.properties"),
    @PropertySource("classpath:messages.properties")
})
public class AppConfigProperty {

}
