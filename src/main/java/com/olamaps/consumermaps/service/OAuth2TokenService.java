package com.olamaps.consumermaps.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OAuth2TokenService {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public OAuth2TokenService(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("my-client")
                .principal("client-credentials")
                .build();
        return Objects.requireNonNull(authorizedClientManager.authorize(authorizeRequest))
                .getAccessToken().getTokenValue();
    }
}
