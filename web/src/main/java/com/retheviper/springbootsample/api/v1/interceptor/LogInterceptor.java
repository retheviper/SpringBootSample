package com.retheviper.springbootsample.api.v1.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom interceptor.
 *
 * @author retheviper
 */
@Slf4j
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        log.info("Request: {}({}) by \'{}\'", request.getRequestURI(), request.getMethod(),
                SecurityContextHolder.getContext().getAuthentication().getName());
        return true;
    }
}