package com.oauth.security.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RobothAutenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       // 1 Decide whetter we want to apply the filter¿

       // 2 Check credentials and (authenticated | reject)
       if(!Objects.equals(request.getHeader("x-robot-secret"), "beep-boop")){
         response.setStatus(HttpStatus.FORBIDDEN.value());
         response.setCharacterEncoding("UTF-8");
         response.setHeader("Content-Type","text/plain");
         response.getWriter().write(" ==== FORBIDDEN ====");
         return;
       }
       // 3 Call next 
       filterChain.doFilter(request, response);
    }

}
