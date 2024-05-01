package com.springboot.blog.security;

import java.io.IOException;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    // Utilites of token provider
    private final JwtTokenProvider jwtTokenProvider;

    // implementacion de la clase user details service
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter( JwtTokenProvider jwtTokenProvider,
    UserDetailsService userDetailsService){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
          //get JWT token from http reqeust 
          String token = getTokenFromRequest(request);

          // validate token
          if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
              // get username from token 
              String username = jwtTokenProvider.getUsername(token);
              
              //load the usser associated with token 
              UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
           
              UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
              
              authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
              
          }

        filterChain.doFilter(request, response);        
    }

    /**
     * Funcion que permite verificar si tiene la palabra Bearer y despues el 
     * token concatenado
     */
    private String getTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        String strBearer ="Bearer ";
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(strBearer)) {
              return bearerToken.substring(strBearer.length(), bearerToken.length());
        } 
        return null;
    }
   

}
