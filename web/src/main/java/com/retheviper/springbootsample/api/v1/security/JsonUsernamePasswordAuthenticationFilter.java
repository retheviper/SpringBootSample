package com.retheviper.springbootsample.api.v1.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Custom UsernamePasswordAuthenticationFilter for credentials with JSON.
 *
 * @author retheviper
 */
public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * The content type of request's header
     */
    private static final String CONTENT_TYPE = "Content-Type";

    /**
     * Parsed data of JSON
     */
    private Map<String, String> jsonRequest;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String obtainUsername(final HttpServletRequest request) {
        return getParameter(request, getUsernameParameter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String obtainPassword(final HttpServletRequest request) {
        return getParameter(request, getPasswordParameter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
            throws AuthenticationException {
        if (headerContentTypeIsJson(request)) {
            final ObjectMapper mapper = new ObjectMapper();
            try {
                this.jsonRequest = mapper.readValue(request.getReader().lines().collect(Collectors.joining()),
                        new TypeReference<Map<String, String>>() {
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        final String username = obtainUsername(request) != null ? obtainUsername(request).trim() : "";
        final String password = obtainPassword(request) != null ? obtainPassword(request).trim() : "";
        final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Get parameter from request.
     *
     * @param request   HTTP Request
     * @param parameter parameter's name
     * @return requested parameter
     */
    private String getParameter(final HttpServletRequest request, final String parameter) {
        return headerContentTypeIsJson(request) ? this.jsonRequest.get(parameter) : request.getParameter(parameter);
    }

    /**
     * Check the header from request's content type is whether JSON or not.
     *
     * @param request HTTP request
     * @return result of check
     */
    private boolean headerContentTypeIsJson(final HttpServletRequest request) {
        return request.getHeader(CONTENT_TYPE).equals(MediaType.APPLICATION_JSON_VALUE);
    }
}