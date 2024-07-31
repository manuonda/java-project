package com.dgarcia.bookstore.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity  
public class SecurityConfig {


    /*@Bean
    /*public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    //     http.authorizeHttpRequests(c -> c.requestMatchers("/actuator/**","/v3/api-docs/**").permitAll()
    //     .anyRequest()
    //     .authenticated())
    //     .sessionManagement( c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //     .cors(CorsConfigurer::disable)
    //     .csrf(CsrfConfigurer::disable)
    //     .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
    //    return  http.build();
    return null;
    }
    */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.authorizeHttpRequests( a -> a.requestMatchers("/swagger-ui/**","/v3/api-docs/**","/api/v1/**","/actuator/**","/")
       .permitAll()
       .anyRequest()
       .authenticated())
       .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .cors(CorsConfigurer::disable)
       .csrf(CsrfConfigurer::disable);

       return http.build();
    }
    
}
