package com.olamaps.consumermaps.restclient;

import com.olamaps.consumermaps.exception.AutoCompleteException;
import com.olamaps.consumermaps.model.AutoCompleteRequest;
import com.olamaps.consumermaps.model.AutoCompleteResponse;
import com.olamaps.consumermaps.model.ErrorMessage;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class AutoCompleteRestClient {

    @Value("${service-url.places.auto-complete}")
    private String placesAutoCompleteUrl;

    private final RestTemplate restTemplate;

    public AutoCompleteRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "fallback")
    @Retry(name = "try")
    public AutoCompleteResponse getAutoComplete(AutoCompleteRequest autoCompleteRequest) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(placesAutoCompleteUrl);
        uri.queryParam("input", autoCompleteRequest.getInput());
        if(!StringUtils.isEmpty(autoCompleteRequest.getOrigin()))
            uri.queryParam("origin", autoCompleteRequest.getOrigin());
        if(!StringUtils.isEmpty(autoCompleteRequest.getLocation()))
            uri.queryParam("location", autoCompleteRequest.getLocation());
        if(!Objects.isNull(autoCompleteRequest.getRadius()) && autoCompleteRequest.getRadius() > 0) {
            uri.queryParam("radius", Math.abs(autoCompleteRequest.getRadius()));
            uri.queryParam("strictbounds", Boolean.TRUE);
        }
        String url = uri.build().toUriString();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<AutoCompleteResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<AutoCompleteResponse>() {});
        return response.getBody();
    }

    public ResponseEntity<ErrorMessage> fallback(Throwable t) throws AutoCompleteException {
        throw new AutoCompleteException(t.getMessage());
    }
}
