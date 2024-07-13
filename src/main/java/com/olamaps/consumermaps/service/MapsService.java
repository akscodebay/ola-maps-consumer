package com.olamaps.consumermaps.service;

import com.olamaps.consumermaps.model.AutoCompleteRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapsService {

    private final RestTemplate restTemplate;

    public MapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "fallback")
    @Retry(name = "try")
    public String getAutoCompleteSuggestions(AutoCompleteRequest autoCompleteRequest) {
        String url = "https://api.olamaps.io/places/v1/autocomplete?input=kempe";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    public String fallback(Throwable t) {
        return "fallback";
    }

}
