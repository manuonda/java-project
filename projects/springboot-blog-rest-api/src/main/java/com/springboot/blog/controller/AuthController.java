package com.springboot.blog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.JWTAuthResponse;
import com.springboot.blog.payload.LoginDTO;
import com.springboot.blog.payload.RegisterDTO;
import com.springboot.blog.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/auth")
@Tag(
    name = "Rest Auth Controller",
    description = "Controller co"
)
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @Operation(

    )
    @ApiResponse(

    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> postMethodName(@Valid @RequestBody LoginDTO loginDto) {
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }
    

    @Operation()
    @ApiResponse()
    @PostMapping(value={"/register","/signup"})
    public ResponseEntity<String> postMethodName(@Valid @RequestBody RegisterDTO register) {
        String response = authService.register(register);
        return ResponseEntity.ok(response);
    }
    




}
