package com.pokeapi.pokemon_service.controllers;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp(){
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8081));
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @AfterEach
    public void tearDown(){
        wireMockServer.stop();
    }

    @Test
    public void testGetPokemon_Success() throws Exception{
       stubFor(WireMock.get(urlEqualTo("/pokemon/pikachu"))
               .willReturn(aResponse()
                       .withStatus(200)
                       .withBody("{\"name\": \"pikachu\", \"abilities\": [] }")));

        mockMvc.perform(get("/pokemon/pikachu").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
                .andExpect(content().string("{\"name\": \"pikachu\", \"abilities\": [] }"));
    }

    @Test
    public void testGetPokemon_NotFound() throws Exception{
        stubFor(WireMock.get(urlEqualTo("/pokemon/qqerPokemon"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withBody("Error: 404 not found")));

        mockMvc.perform(get("/pokemon/qqerPokemon").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Error: 404 not found"));
    }

    @Test
    public void testGetPokemon_InternalServerError() throws Exception{
        stubFor(WireMock.get(urlEqualTo("/pokemon/qqerPokemon"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withBody("Error500 INTERNAL_SERVER_ERROR 500 Internal Server Error from GET http://localhost:8081/pokemon/qqerPokemon")));

        mockMvc.perform(get("/pokemon/qqerPokemon").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error500 INTERNAL_SERVER_ERROR 500 Internal Server Error from GET http://localhost:8081/pokemon/qqerPokemon"));
    }

}