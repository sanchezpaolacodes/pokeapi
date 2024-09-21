package com.pokeapi.pokemon_service.repositories;

import com.pokeapi.pokemon_service.configuration.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@RequiredArgsConstructor
public class PokemonRepository {

    private final WebClient webClient;

    public String findPokemonByName(String nomePokemon){
        return webClient.get()
                .uri("/pokemon/{nomePokemon}", nomePokemon)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
