package com.olamaps.consumermaps.interceptor;

import com.olamaps.consumermaps.model.CorrelationId;
import com.olamaps.consumermaps.service.OAuth2TokenService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static com.olamaps.consumermaps.constant.Constant.CORRELATION_ID;
import static com.olamaps.consumermaps.constant.Constant.REQUEST_ID;

@Configuration
public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    @Value("${secrets.api-key}")
    private String apiKey;

    private final OAuth2TokenService auth2TokenService;

    public HeaderInterceptor(OAuth2TokenService auth2TokenService) {
        this.auth2TokenService = auth2TokenService;
    }

    @Override
    public @NonNull ClientHttpResponse intercept(HttpRequest request, byte @NonNull [] body, @NonNull ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().setBearerAuth(auth2TokenService.getAccessToken());
        String requestId = UUID.randomUUID().toString();
        request.getHeaders().add(REQUEST_ID, requestId);
        request.getHeaders().add(CORRELATION_ID, CorrelationId.getCorrelationId());

        URI uri = getUriWithApiKey(request);
        HttpRequest modifiedRequest = new HttpRequestWrapper(request){
            @Override
            public URI getURI() {
                return uri;
            }
        };

        ClientHttpResponse response = execution.execute(modifiedRequest, body);
        response.getHeaders().add(CORRELATION_ID, CorrelationId.getCorrelationId());
        return response;
    }

    private URI getUriWithApiKey(HttpRequest request) {
        URI requestURI = request.getURI();
        String query = requestURI.getQuery();

        query = query == null ? "api_key=" + apiKey : query + "&api_key=" + apiKey;
        URI uri  = null;
        try {
            uri = new URI(
                    requestURI.getScheme(),
                    requestURI.getAuthority(),
                    requestURI.getPath(),
                    query,
                    requestURI.getFragment()
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return uri;
    }
}
