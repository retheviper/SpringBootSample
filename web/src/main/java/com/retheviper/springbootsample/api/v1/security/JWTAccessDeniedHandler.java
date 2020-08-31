package com.retheviper.springbootsample.api.v1.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom AccessDeniedHandler.
 *
 * @author retheviper
 */
@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response,
                       final AccessDeniedException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }
}