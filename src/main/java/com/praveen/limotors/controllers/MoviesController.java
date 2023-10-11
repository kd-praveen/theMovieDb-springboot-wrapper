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
@RequestMapping("/api/v3/li-motors")
public class MoviesController {

    private final WebClient webClient;

    public MoviesController() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.themoviedb.org")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YzQ5NjQ0ODEwMjI3OWQ2NjZjMjg1NzVhYzcwZTZiMiIsInN1YiI6IjY1MjM3Mzk5MGNiMzM1MTZmODdmMGEwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.e7OvOzLWJAoOilA3bwoj__aVL6I2XOwVF9ZfVXWFXq0")
                .build();
    }

    @GetMapping("/movie/changes")
    public Mono<JsonNode> getMovieList(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "start_date", required = false, defaultValue = "#{T(java.time.LocalDate).now()}") String startDate,
            @RequestParam(name = "end_date", required = false, defaultValue = "#{T(java.time.LocalDate).now()}") String endDate) {

        String url = UriComponentsBuilder.fromPath("/3/movie/changes")
                .queryParam("page", page)
                .queryParamIfPresent("start_date", Optional.ofNullable(startDate))
                .queryParamIfPresent("end_date", Optional.ofNullable(endDate))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/person/changes")
    public Mono<JsonNode> getPeopleList(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "start_date", required = false, defaultValue = "#{T(java.time.LocalDate).now()}") String startDate,
            @RequestParam(name = "end_date", required = false, defaultValue = "#{T(java.time.LocalDate).now()}") String endDate) {

        String url = UriComponentsBuilder.fromPath("/3/person/changes")
                .queryParam("page", page)
                .queryParamIfPresent("start_date", Optional.ofNullable(startDate))
                .queryParamIfPresent("end_date", Optional.ofNullable(endDate))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/discover/movie")
    public Mono<JsonNode> getDiscoverMovie(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "include_adult", required = false, defaultValue = "false") boolean include_adult,
            @RequestParam(name = "include_video", required = false, defaultValue = "false") boolean include_video,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "sort_by", required = false, defaultValue = "popularity.desc") String sort_by) {

        String url = UriComponentsBuilder.fromPath("/3/discover/movie")
                .queryParam("page", page)
                .queryParamIfPresent("include_adult", Optional.ofNullable(include_adult))
                .queryParamIfPresent("include_video", Optional.ofNullable(include_video))
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("sort_by", Optional.ofNullable(sort_by))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/now_playing")
    public Mono<JsonNode> getNowPlayingMovies(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "region", required = false) String region) {

        String url = UriComponentsBuilder.fromPath("/3/movie/now_playing")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("region", Optional.ofNullable(region))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/popular")
    public Mono<JsonNode> getPopularMovies(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "region", required = false) String region) {

        String url = UriComponentsBuilder.fromPath("/3/movie/popular")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("region", Optional.ofNullable(region))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/top_rated")
    public Mono<JsonNode> getTopratedMovies(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "region", required = false) String region) {

        String url = UriComponentsBuilder.fromPath("/3/movie/top_rated")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("region", Optional.ofNullable(region))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/upcoming")
    public Mono<JsonNode> getUpcomingMovies(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "region", required = false) String region) {

        String url = UriComponentsBuilder.fromPath("/3/movie/upcoming")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .queryParamIfPresent("region", Optional.ofNullable(region))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}")
    public Mono<JsonNode> getMovieDetails(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @PathVariable int movie_id) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/alternative_titles")
    public Mono<JsonNode> getAlternativeMovieTitles(
            @RequestParam(name = "country", required = false, defaultValue = "IN") String country,
            @PathVariable int movie_id) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/alternative_titles")
                .queryParamIfPresent("country", Optional.ofNullable(country))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/images")
    public Mono<JsonNode> getMovieImages(
            @RequestParam(name = "language", required = false, defaultValue = "en") String language,
            @PathVariable int movie_id) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/images")
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/keywords")
    public Mono<JsonNode> getMovieImages(@PathVariable int movie_id) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/keywords")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/latest")
    public Mono<JsonNode> getLatestMovies() {

        String url = UriComponentsBuilder.fromPath("/3/movie/latest")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/lists")
    public Mono<JsonNode> getMovieLists(
            @PathVariable int movie_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/lists")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/recommendations")
    public Mono<JsonNode> getMovieRecommendations(
            @PathVariable int movie_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/recommendations")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/similar")
    public Mono<JsonNode> getMovieSimilar(
            @PathVariable int movie_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/similar")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/videos")
    public Mono<JsonNode> getMovieVideos(
            @PathVariable int movie_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/videos")
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/translations")
    public Mono<JsonNode> getMovieNameTranslations(
            @PathVariable int movie_id,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/translations")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/movie/{movie_id}/watch/providers")
    public Mono<JsonNode> getMovieWatchProviders(@PathVariable int movie_id) {

        String url = UriComponentsBuilder.fromPath("/3/movie/" + movie_id + "/watch/providers")
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("/person/popular")
    public Mono<JsonNode> getPopularPersonLists(
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        String url = UriComponentsBuilder.fromPath("/3/person/popular")
                .queryParam("page", page)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("trending/all/{time_window}")
    public Mono<JsonNode> getAllTrendingShows(
            @PathVariable String time_window,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/trending/all/" + time_window)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("trending/movie/{time_window}")
    public Mono<JsonNode> getTrendingMovies(
            @PathVariable String time_window,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/trending/movie/" + time_window)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    @GetMapping("trending/person/{time_window}")
    public Mono<JsonNode> getTrendingPersons(
            @PathVariable String time_window,
            @RequestParam(name = "language", required = false, defaultValue = "en-US") String language) {

        String url = UriComponentsBuilder.fromPath("/3/trending/person/" + time_window)
                .queryParamIfPresent("language", Optional.ofNullable(language))
                .toUriString();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }


}
