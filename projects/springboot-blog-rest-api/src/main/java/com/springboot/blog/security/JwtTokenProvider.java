package com.springboot.blog.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.blog.exception.BlogApiException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Class Utility para generation Tokens
 * and Validate Tokens
 */

@Component
public class JwtTokenProvider {



    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.expiration-time}")
    private Long jwtExpirationDate;

    //generate JWT Token 
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();

        Date expirateDate= new Date( currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(expirateDate)
        .signWith(key())
        .compact();


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
        try {
       
            Jwts.parser()
            .verifyWith((SecretKey) key())
            .build()
            .parse(token);
            
            return true;
        } catch (MalformedJwtException e) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch( ExpiredJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
        } catch( UnsupportedJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsuportted JWT Token");
        } catch( IllegalArgumentException illegalArgumentException ){
            throw new  BlogApiException(HttpStatus.BAD_REQUEST, "Jwt claims string is null or empty");
        }
       
    }
}
