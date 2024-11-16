package com.olamaps.consumermaps.restclient;

import com.olamaps.consumermaps.exception.GeocodeException;
import com.olamaps.consumermaps.model.GeocodeRequest;
import com.olamaps.consumermaps.model.GeocodeResponse;
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

@Service
public class AddressGeocodeRestClient {

    @Value("${service-url.places.geocode}")
    private String placesGeocodeUrl;

    private final RestTemplate restTemplate;

    public AddressGeocodeRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "breaker", fallbackMethod = "fallback")
    @Retry(name = "try")
    public GeocodeResponse getAddressGeocode(GeocodeRequest geocodeRequest) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(placesGeocodeUrl);
        uri.queryParam("address", geocodeRequest.getAddress());
        if(!StringUtils.isEmpty(geocodeRequest.getBounds()))
            uri.queryParam("bounds", geocodeRequest.getBounds());
        if(!StringUtils.isEmpty(geocodeRequest.getLanguage()))
            uri.queryParam("language", geocodeRequest.getLanguage());
        String url = uri.build().toUriString();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<GeocodeResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<GeocodeResponse>() {});
        return response.getBody();
    }

    public GeocodeResponse fallback(Throwable t) throws GeocodeException {
        throw new GeocodeException(t.getMessage());
    }
}
