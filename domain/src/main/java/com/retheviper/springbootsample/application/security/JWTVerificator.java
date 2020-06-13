package com.retheviper.springbootsample.application.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.retheviper.springbootsample.common.constant.type.HeaderType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

/**
 * Custom JWT verificator.
 *
 * @author retheviper
 */
@Component
@RequiredArgsConstructor
public class JWTVerificator {

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
        return Jwts.parser().setSigningKey(this.settings.getSecretKey()).parseClaimsJws(token).getBody().getSubject();
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
            final Jws<Claims> claims = Jwts.parser().setSigningKey(this.settings.getSecretKey()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
