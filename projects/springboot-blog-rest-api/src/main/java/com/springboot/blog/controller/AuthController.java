package com.springboot.blog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.LoginDTO;
import com.springboot.blog.payload.RegisterDTO;
import com.springboot.blog.service.AuthService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> postMethodName(@Valid @RequestBody LoginDTO loginDto) {
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }
    

    @PostMapping(value={"/register","/signup"})
    public ResponseEntity<String> postMethodName(@Valid @RequestBody RegisterDTO register) {
        String response = authService.register(register);
        return ResponseEntity.ok(response);
    }
    




}
