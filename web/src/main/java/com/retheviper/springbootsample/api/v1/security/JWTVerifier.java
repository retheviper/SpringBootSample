package com.retheviper.springbootsample.api.v1.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.retheviper.springbootsample.common.constant.type.HeaderType;

import lombok.RequiredArgsConstructor;

/**
 * Custom JWT verifier.
 *
 * @author retheviper
 */
@Component
@RequiredArgsConstructor
public class JWTVerifier {

    /**
     * JWT Settings
     */
    private final JWTSettings settings;

    /**
     * User details service
     */
    private final UserDetailsService service;

    /**
     * Get authentication with JWT.
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(final String token) {
        final UserDetails userDetails = this.service.loadUserByUsername(this.getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Get subject from JWT.
     *
     * @param token JWT
     * @return the subject(username)
     */
    public String getSubject(final String token) {
        return decodeToken(token).getSubject();
    }

    /**
     * Parse JWT from request's header.
     *
     * @param request HTTP Request
     * @return
     */
    public String resolveToken(final HttpServletRequest request) {
        return request.getHeader(HeaderType.AUTH_TOKEN.getValue());
    }

    /**
     * Check is the JWT validated.
     *
     * @param token JWT
     * @return result of validation check
     */
    public boolean validateToken(final String token) {
        try {
            return !decodeToken(token).getExpiresAt().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Decode JWT.
     * @param token
     * @return
     */
    private DecodedJWT decodeToken(final String token) {
        return JWT.require(this.settings.getAlgorithm()).build().verify(token);
    }
}
