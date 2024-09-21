package com.pokeapi.pokemon_service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${pokeapi.base-url}")
   private String baseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder){
       return builder.baseUrl(baseUrl) .build();
    }

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }

}
