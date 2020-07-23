package com.retheviper.springbootsample.application.config;

import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for commonly used beans for this application.
 *
 * @author retheviper
 */
@Configuration
public class CommonBeansConfig {

    /**
     * A ModelMapper for map data for form, view model, DTO, entity classes.
     */
    @Bean
    public ModelMapper mapper() {
        final ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    /**
     * A password encoder for encode or decode Member's password.
     */
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Pattern for check string is numeric.
     */
    @Bean
    public Pattern numericPatternMatcher() {
        return Pattern.compile("-?\\d+(\\.\\d+)?");
    }
}