package com.oauth.security;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(c -> c.disable())
        .authorizeHttpRequests( request -> {
           request.requestMatchers(HttpMethod.GET,"/login").permitAll();
           request.anyRequest().authenticated();
        })
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
