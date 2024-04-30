package com.springboot.blog.config;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {



    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.expiration-time}")
    private Long jwtExpirationDate;

    //generate JWT Token 
    public String generateToken(Authentication authentication){
        String username = authentication.getUsername();
        Date currentDate = new Date();

        Date expirateDate= new Date( currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(expirateDate)
        .signWith(key())
        .compact();

        return token;

    }

    //get username form JWT Token
    public String getUsername(String token){
       return Jwts.parser()
       .verifyWith((SecretKey) key())
       .build()
       .parseSignedClaims(token)
       .getPayload()
       .getSubject();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    // validate JWT Token 
    public boolean validateToken(String token){
        Jwts.parser()
        .verifyWith((SecretKey) key())
        .build()
        .parse(token);
        
        return true;
    }
}
