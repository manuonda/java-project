package com.oauth.security.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PrivateFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getHeader("x-prohibido") != null && request.getHeader("x-prohibido").equals("si")){
          response.setStatus(HttpStatus.FORBIDDEN.value());
          response.getWriter().write("*** FORBIDDEN ***");  
          return;
        } 
        filterChain.doFilter(request, response);
    }


}
