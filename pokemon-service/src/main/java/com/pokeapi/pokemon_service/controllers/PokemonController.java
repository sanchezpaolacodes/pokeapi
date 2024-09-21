package com.pokeapi.pokemon_service.controllers;

import com.pokeapi.pokemon_service.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/pokemon/{nomePokemon}")
    public ResponseEntity<String> getPokemon(@PathVariable String nomePokemon){
        return ResponseEntity.ok(pokemonService.getPokemonByName(nomePokemon));
    }
}
