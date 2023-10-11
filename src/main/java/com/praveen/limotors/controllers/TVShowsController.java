package com.praveen.limotors.controllers;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v3/li-motors")
public class TVShowsController {

    private final WebClient webClient;

    public TVShowsController() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.themoviedb.org")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YzQ5NjQ0ODEwMjI3OWQ2NjZjMjg1NzVhYzcwZTZiMiIsInN1YiI6IjY1MjM3Mzk5MGNiMzM1MTZmODdmMGEwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e7OvOzLWJAoOilA3bwoj__aVL6I2XOwVF9ZfVXWFXq0")
                .build();
    }

    @GetMapping("/discover/tv")
    public Mono<JsonNode> getDiscoverTv(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "include_adult", required = false, defaultValue = "false") boolean include_adult,
            @RequestParam(name = "include_null_first_air_dates", required = false, defaultValue = "false") boolean include_null_first_air_dates,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "sort_by", required = false, defaultValue = "popularity.desc") String sort_by) {

        String url = UriComponentsBuilder.fromPath("/3/discover/tv")
                .queryParam("page", page)
                .queryParamIfPresent("include_adult", Optional.ofNullable(include_adult))
                .queryParamIfPresent("include_null_first_air_dates", Optional.ofNullable(include_null_first_air_dates))
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("sort_by", Optional.ofNullable(sort_by))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/changes")
    public Mono<JsonNode> getTvList(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "start_date", required = false, defaultValue = "#{T(java.time.LocalDate).now()}") String startDate,
            @RequestParam(name = "end_date", required = false, defaultValue = "#{T(java.time.LocalDate).now()}") String endDate) {

        String url = UriComponentsBuilder.fromPath("/3/tv/changes")
                .queryParam("page", page)
                .queryParamIfPresent("start_date", Optional.ofNullable(startDate))
                .queryParamIfPresent("end_date", Optional.ofNullable(endDate))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}")
    public Mono<JsonNode> getTVShowDetails(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @PathVariable int series_id) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/alternative_titles")
    public Mono<JsonNode> getAlternativeTVShowTitles(
            @RequestParam(name = "country", required = false, defaultValue = "IN") String country,
            @PathVariable int series_id) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/alternative_titles")
                .queryParamIfPresent("country", Optional.ofNullable(country))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/images")
    public Mono<JsonNode> getTVShowImages(
            @RequestParam(name = "language", required = false, defaultValue = "en") String language,
            @PathVariable int series_id) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/images")
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/keywords")
    public Mono<JsonNode> getTVShowImages(@PathVariable int series_id) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/keywords")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/latest")
    public Mono<JsonNode> getLatestTVShows() {

        String url = UriComponentsBuilder.fromPath("/3/tv/latest")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/recommendations")
    public Mono<JsonNode> getTVShowRecommendations(
            @PathVariable int series_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/recommendations")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/similar")
    public Mono<JsonNode> getTVShowSimilar(
            @PathVariable int series_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/similar")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/videos")
    public Mono<JsonNode> getTVShowVideos(
            @PathVariable int series_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/videos")
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/translations")
    public Mono<JsonNode> getTVShowNameTranslations(
            @PathVariable int series_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/translations")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/{series_id}/watch/providers")
    public Mono<JsonNode> getTVShowWatchProviders(@PathVariable int series_id) {

        String url = UriComponentsBuilder.fromPath("/3/tv/" + series_id + "/watch/providers")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/airing_today")
    public Mono<JsonNode> getTVShowsAiringToday(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "timezone", required = false) String timezone,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/tv/airing_today")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("timezone", Optional.ofNullable(timezone))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/on_the_air")
    public Mono<JsonNode> getTVShowsOnAir(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "timezone", required = false) String timezone,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/tv/on_the_air")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("timezone", Optional.ofNullable(timezone))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/popular")
    public Mono<JsonNode> getPopularTVShows(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/tv/popular")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/tv/top_rated")
    public Mono<JsonNode> getTopRatedTVShows(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/tv/top_rated")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("trending/tv/{time_window}")
    public Mono<JsonNode> getTrendingTVShows(
            @PathVariable String time_window,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/trending/tv/" + time_window)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
