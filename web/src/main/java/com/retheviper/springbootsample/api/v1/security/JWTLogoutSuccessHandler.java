package com.retheviper.springbootsample.api.v1.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Custom LogoutSuccessHandler.
 *
 * @author retheviper
 */
@Component
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
    }
}
