package com.retheviper.springbootsample.application.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;

/**
 * Custom AuthenticationFilter.
 *
 * @author retheviper
 */
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends GenericFilterBean {

    /**
     * JWT Verificator
     */
    private final JWTVerificator verificator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        final String token = this.verificator.resolveToken((HttpServletRequest) request);
        if (token != null && this.verificator.validateToken(token)) {
            final Authentication auth = this.verificator.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}