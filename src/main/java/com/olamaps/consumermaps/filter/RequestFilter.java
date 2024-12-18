package com.olamaps.consumermaps.filter;

import com.olamaps.consumermaps.model.CorrelationId;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

import static com.olamaps.consumermaps.constant.Constant.REQUEST_ID;

@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getHeader(REQUEST_ID) == null) {
            CorrelationId.setCorrelationId(UUID.randomUUID().toString());
        }
        else {
            CorrelationId.setCorrelationId(request.getHeader(REQUEST_ID));
        }
        request.setAttribute(REQUEST_ID, CorrelationId.getCorrelationId());

        filterChain.doFilter(request, servletResponse);
    }
}
