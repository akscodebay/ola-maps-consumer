package com.olamaps.consumermaps.interceptor;

import com.olamaps.consumermaps.model.CorrelationId;
import com.olamaps.consumermaps.service.OAuth2TokenService;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Configuration
public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2TokenService auth2TokenService;

    public HeaderInterceptor(OAuth2TokenService auth2TokenService){
        this.auth2TokenService = auth2TokenService;
    }

    @Override
    public @NonNull ClientHttpResponse intercept(HttpRequest request, byte @NonNull [] body, @NonNull ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().setBearerAuth(auth2TokenService.getAccessToken());
        String requestId = UUID.randomUUID().toString();
        String correlationId = CorrelationId.getCorrelationId();
        request.getHeaders().add("X-Request-Id", requestId);
        if (Objects.isNull(correlationId)) {
            correlationId = UUID.randomUUID().toString();
            CorrelationId.setCorrelationId(correlationId);
        }
        request.getHeaders().add("X-Correlation-Id", correlationId);
        ClientHttpResponse response = execution.execute(request, body);
        response.getHeaders().add("X-Correlation-Id", correlationId);
        return response;
    }
}
