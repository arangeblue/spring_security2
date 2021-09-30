package com.orangeblue.springsecurity.jwt;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;

/**
 *@title : JwtUsernameAndPasswordAuthenticationFilter
 *@author : wikyubok 
 *@date : "2021-09-29 18:24:23"
 *@description : ""
*/


public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {



    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;



    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }




    /**
     *@date : "2021-09-29 18:26:41"
     *@description : jwt 과정 중 request의 credential(username, password)을 받아서 validates 하는 것
    */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
    
        
        
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
      
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication); // if username exists and password is collect, authenticate this request;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        


    
    }


    /**
     *@date : "2021-09-29 18:28:08"
     *@description : attemptAuthentication메서드에서 validate를 성공하면 successfulAuthentication 메서드 실행
                     token을 만들고 만기일을 지정한 후 response에 token을 담아서 send하는 과정
    */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        long currentTime = System.currentTimeMillis();

        String token = Jwts.builder().setSubject(authResult.getName()) // Name으로 token(payload)을 만드는 과정
                .claim("authorities", authResult.getAuthorities()).setIssuedAt(new Date(currentTime))
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays()))) // 1일 만기
                .signWith(secretKey) // as long as possible
                .compact();

        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token); // send response included token
    }
    


}






