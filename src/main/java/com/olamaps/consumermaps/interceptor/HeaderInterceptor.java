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
import java.util.UUID;

import static com.olamaps.consumermaps.constant.Constant.CORRELATION_ID;
import static com.olamaps.consumermaps.constant.Constant.REQUEST_ID;

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
        request.getHeaders().add(REQUEST_ID, requestId);
        request.getHeaders().add(CORRELATION_ID, CorrelationId.getCorrelationId());
        ClientHttpResponse response = execution.execute(request, body);
        response.getHeaders().add(CORRELATION_ID, CorrelationId.getCorrelationId());
        return response;
    }
}
