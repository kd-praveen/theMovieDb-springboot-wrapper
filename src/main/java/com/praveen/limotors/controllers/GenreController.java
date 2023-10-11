package com.praveen.limotors.controllers;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v3/li-motors")
public class GenreController {

    private final WebClient webClient;

    public GenreController() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.themoviedb.org")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YzQ5NjQ0ODEwMjI3OWQ2NjZjMjg1NzVhYzcwZTZiMiIsInN1YiI6IjY1MjM3Mzk5MGNiMzM1MTZmODdmMGEwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e7OvOzLWJAoOilA3bwoj__aVL6I2XOwVF9ZfVXWFXq0")
                .build();
    }

    @GetMapping("/genre/movie/list")
    public Mono<JsonNode> getGenreMovieList(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/genre/movie/list")
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/genre/tv/list")
    public Mono<JsonNode> getGenreTvList(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/genre/tv/list")
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
