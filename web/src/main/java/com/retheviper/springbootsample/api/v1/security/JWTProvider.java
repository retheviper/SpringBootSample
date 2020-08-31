package com.retheviper.springbootsample.api.v1.security;

import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
        final Date start = new Date();
        final Date end = new Date(start.getTime() + this.settings.getTokenValidDutarion());
        return JWT.create()
                .withSubject(uid)
                .withClaim("roles", roles)
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(this.settings.getAlgorithm());
    }
}
