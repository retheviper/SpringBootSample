package com.retheviper.springbootsample.api.v1.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Custom AuthenticationFailureHandler.
 *
 * @author retheviper
 */
@Component
public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }
}