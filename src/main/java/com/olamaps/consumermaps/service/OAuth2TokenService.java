package com.olamaps.consumermaps.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Service
public class OAuth2TokenService {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    private static OAuth2AccessToken accessToken = null;

    public OAuth2TokenService(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getAccessToken() {
        if (accessToken != null && Objects.requireNonNull(accessToken.getExpiresAt()).isAfter(Instant.now())) {
            return accessToken.getTokenValue();
        }
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("my-client")
                .principal("client-credentials")
                .build();
        accessToken = Objects.requireNonNull(authorizedClientManager.authorize(authorizeRequest))
                .getAccessToken();
        return accessToken.getTokenValue();
    }
}
