package com.demo.oragejobsite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.oragejobsite.util.TokenProvider;

@Configuration
public class AppConfig {

    @Value("${jwt.refreshSecret}")
    private String refreshTokenSecret;

    @Bean(name = "customTokenProvider")
    public TokenProvider tokenProvider() {
        // Your bean configuration
        return new TokenProvider();
    }
  
}
