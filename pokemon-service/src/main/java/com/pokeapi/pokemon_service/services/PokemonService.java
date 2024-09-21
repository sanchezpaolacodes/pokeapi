package com.pokeapi.pokemon_service.services;

import com.pokeapi.pokemon_service.repositories.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    public String getPokemonByName(String nomePokemon){
        return pokemonRepository.findPokemonByName(nomePokemon);
    }
}
