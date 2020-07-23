package com.retheviper.springbootsample.api.v1.security;

import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.algorithms.Algorithm;

import lombok.Getter;

/**
 * Custom JWT Settings.
 *
 * @author retheviper
 */
@Component
public class JWTSettings {

    /**
     * Secret key for JWT Signature
     */
    @Value("spring.jwt.secret:!secret!")
    private String secretKey;

    /**
     * JWT Token only valid for only 1 hour
     */
    @Getter
    private final long tokenValidDutarion = 1000L * 60L * 60L;

    /**
     * Encode secretKey to base64 string
     */
    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    /**
     * Get algorithm for JWT.
     *
     * @return algorithm with secret key setted
     */
    public Algorithm getAlgorithm() {
        return Algorithm.HMAC512(this.secretKey);
    }
}
