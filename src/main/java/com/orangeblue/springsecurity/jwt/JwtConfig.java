package com.orangeblue.springsecurity.jwt;

import javax.crypto.SecretKey;

import com.google.common.net.HttpHeaders;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;


@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;


    public JwtConfig() {

    }
    

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return this.tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Integer getTokenExpirationAfterDays() {
        return this.tokenExpirationAfterDays;
    }

    public void setTokenExpirationAfterDays(Integer tokenExpirationAfterDays) {
        this.tokenExpirationAfterDays = tokenExpirationAfterDays;
    }

    

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
