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
@RequestMapping("/api/v3/li-motors")
public class ConfigurationContoller {

    private final WebClient webClient;

    public ConfigurationContoller() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.themoviedb.org")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YzQ5NjQ0ODEwMjI3OWQ2NjZjMjg1NzVhYzcwZTZiMiIsInN1YiI6IjY1MjM3Mzk5MGNiMzM1MTZmODdmMGEwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e7OvOzLWJAoOilA3bwoj__aVL6I2XOwVF9ZfVXWFXq0")
                .build();
    }

    @GetMapping("/configuration")
    public Mono<JsonNode> getConfiguration() {

        String url = UriComponentsBuilder.fromPath("/3/configuration")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/configuration/languages")
    public Mono<JsonNode> getConfigurationLanguages() {

        String url = UriComponentsBuilder.fromPath("/3/configuration/languages")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/configuration/jobs")
    public Mono<JsonNode> getConfigurationJobs() {

        String url = UriComponentsBuilder.fromPath("/3/configuration/jobs")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/configuration/countries")
    public Mono<JsonNode> getConfigurationCountries(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/configuration/countries")
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/configuration/primary_translations")
    public Mono<JsonNode> getConfigurationTranslations() {

        String url = UriComponentsBuilder.fromPath("/3/configuration/primary_translations")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/configuration/timezones")
    public Mono<JsonNode> getConfigurationTimezones() {

        String url = UriComponentsBuilder.fromPath("/3/configuration/timezones")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
