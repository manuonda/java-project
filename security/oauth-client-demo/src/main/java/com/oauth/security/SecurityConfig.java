package com.oauth.security;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oauth.provider.ManuAuthenticationProvider;
import com.oauth.security.filter.PrivateFilter;
import com.oauth.security.filter.RobothAutenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(c -> c.disable())
        .authorizeHttpRequests( authorize -> {
           authorize.requestMatchers("/login").permitAll();
           authorize.anyRequest().authenticated();
        })
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .logout(l -> l.logoutSuccessUrl("/login"))
        .addFilterBefore(new PrivateFilter(),AuthorizationFilter.class)
        .addFilterBefore(new RobothAutenticationFilter(), AuthorizationFilter.class)
        .authenticationProvider(new ManuAuthenticationProvider())
        .oauth2Login(Customizer.withDefaults());
        return http.build();
    }   


    @Bean
    ApplicationListener<AuthenticationSuccessEvent> listener(){
        return (evt) -> {
            var auth = evt.getAuthentication();
            System.out.println("[%s] logged in as [%s]".formatted(
                auth.getName(),
                auth.getClass().getSimpleName()
            )
           );
        };
    }
}
