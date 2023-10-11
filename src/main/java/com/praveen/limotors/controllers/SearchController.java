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
public class SearchController {

    private final WebClient webClient;

    public SearchController() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.themoviedb.org")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YzQ5NjQ0ODEwMjI3OWQ2NjZjMjg1NzVhYzcwZTZiMiIsInN1YiI6IjY1MjM3Mzk5MGNiMzM1MTZmODdmMGEwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e7OvOzLWJAoOilA3bwoj__aVL6I2XOwVF9ZfVXWFXq0")
                .build();
    }

    @GetMapping("/search/collection")
    public Mono<JsonNode> searchCollection(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "include_adult", required = false, defaultValue = "false") boolean include_adult,
            @RequestParam(name = "query", required = true, defaultValue = "null") String query,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "region", required = false, defaultValue = "IN") String region) {

        String url = UriComponentsBuilder.fromPath("/3/search/collection")
                .queryParam("page", page)
                .queryParam("query", query)
                .queryParamIfPresent("include_adult", Optional.ofNullable(include_adult))
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("region", Optional.ofNullable(region))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/search/company")
    public Mono<JsonNode> searchCompany(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "query", required = true, defaultValue = "null") String query) {

        String url = UriComponentsBuilder.fromPath("/3/search/company")
                .queryParam("page", page)
                .queryParam("query", query)
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/search/keyword")
    public Mono<JsonNode> searchKeyword(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "query", required = true, defaultValue = "null") String query) {

        String url = UriComponentsBuilder.fromPath("/3/search/keyword")
                .queryParam("page", page)
                .queryParam("query", query)
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/search/movie")
    public Mono<JsonNode> searchMovie(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "include_adult", required = false, defaultValue = "false") boolean include_adult,
            @RequestParam(name = "query", required = true, defaultValue = "null") String query,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "primary_release_year", required = false) String primary_release_year,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "region", required = false, defaultValue = "IN") String region) {

        String url = UriComponentsBuilder.fromPath("/3/search/movie")
                .queryParam("page", page)
                .queryParam("query", query)
                .queryParamIfPresent("include_adult", Optional.ofNullable(include_adult))
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("primary_release_year", Optional.ofNullable(primary_release_year))
                .queryParamIfPresent("year", Optional.ofNullable(year))
                .queryParamIfPresent("region", Optional.ofNullable(region))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/search/multi")
    public Mono<JsonNode> searchMultipleFiles(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "include_adult", required = false, defaultValue = "false") boolean include_adult,
            @RequestParam(name = "query", required = true, defaultValue = "null") String query,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/search/multi")
                .queryParam("page", page)
                .queryParam("query", query)
                .queryParamIfPresent("include_adult", Optional.ofNullable(include_adult))
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/search/person")
    public Mono<JsonNode> searchPersons(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "include_adult", required = false, defaultValue = "false") boolean include_adult,
            @RequestParam(name = "query", required = true, defaultValue = "null") String query,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/search/person")
                .queryParam("page", page)
                .queryParam("query", query)
                .queryParamIfPresent("include_adult", Optional.ofNullable(include_adult))
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
