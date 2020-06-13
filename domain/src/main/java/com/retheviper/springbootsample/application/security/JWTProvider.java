package com.retheviper.springbootsample.application.security;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

/**
 * Cusotm JWT provider.
 *
 * @author retheviper
 */
@Component
@RequiredArgsConstructor
public class JWTProvider {

    /**
     * JWT Settings
     */
    private final JWTSettings settings;

    /**
     * Create JWT Token.
     *
     * @param uid
     * @param roles
     * @return authentication
     */
    public String createToken(final String uid, final List<String> roles) {
        final Claims claims = Jwts.claims().setSubject(uid);
        claims.put("roles", roles);
        final Date start = new Date();
        final Date end = new Date(start.getTime() + this.settings.getTokenValidDutarion());
        return Jwts.builder()
                // Token Data
                .setClaims(claims)
                // Date of Token Created
                .setIssuedAt(start)
                // Expire Date of Token
                .setExpiration(end)
                // Encryptiong of Token
                .signWith(SignatureAlgorithm.HS256, this.settings.getSecretKey())
                .compact();
    }
}
