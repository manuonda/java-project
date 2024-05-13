package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.blog.security.JwtAuthenticationEntryPoint;
import com.springboot.blog.security.JwtAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@EnableMethodSecurity
@SecurityScheme(
    name = "Bear Authentication",
    type= SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"    
)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    // Manejaror de entrada de exceptions
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    //
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        // authorize -> authorize.anyRequest().authenticated()
                        authorize -> authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()) // manejador de exceptions de authentication
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint))
                .httpBasic(Customizer.withDefaults()) // configuracion maneja de sessions
                .sessionManagement(
                        // Politica Stateless : no debe crear session para el usuario,ni mantendra
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /***
         * estás diciendo a Spring Security que agregue tu filtro
         * jwtAuthenticationFilter antes del filtro de autenticación por nombre de
         * usuario y contraseña (UsernamePasswordAuthenticationFilter). Esto garantiza
         * que tu filtro personalizado se ejecute primero y tenga la oportunidad de
         * manejar la autenticación antes de que Spring Security intente autenticar
         * utilizando el método estándar de nombre de usuario y contraseña.
         */

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails userDavid = User.builder()
    // .username("david")
    // .password(passwordEncoder().encode("david"))
    // .roles("USER").build();
    //
    // UserDetails userAdmin = User.builder()
    // .username("admin")
    // .password(passwordEncoder().encode("admin"))
    // .roles("ADMIN").build();
    //
    // return new InMemoryUserDetailsManager(userAdmin, userDavid);
    //
    // }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
