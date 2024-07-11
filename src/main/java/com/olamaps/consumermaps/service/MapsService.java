package com.olamaps.consumermaps.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapsService {

    private final RestTemplate restTemplate;
    private final OAuth2TokenService auth2TokenService;

    public MapsService(OAuth2TokenService auth2TokenService) {
        this.restTemplate = new RestTemplate();
        this.auth2TokenService = auth2TokenService;
    }

    public String getAutoCompleteSuggestions(){
        String url = "https://api.olamaps.io/places/v1/autocomplete?input=kempe";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(auth2TokenService.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }

}
