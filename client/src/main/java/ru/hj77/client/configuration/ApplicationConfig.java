package ru.hj77.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public CardSettings getCardSettings(){
        return new CardSettings();
    }
    @Bean
    public String getJWT(){
        return "";
    }
}
