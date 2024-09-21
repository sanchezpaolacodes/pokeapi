package com.pokeapi.pokemon_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handlerWebClientResponseException(WebClientResponseException e){
        if(e.getStatusCode() == HttpStatus.NOT_FOUND){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: 404 not found");
        }
        return ResponseEntity.status(e.getStatusCode())
                .body("Error" + e.getStatusCode() + " " + e.getMessage());
    }
}
