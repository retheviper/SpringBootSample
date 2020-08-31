package com.retheviper.springbootsample.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * Congifuration class for Spring Data JPA with custom auditor.
 *
 * @author retheviper
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableTransactionManagement
public class PersistanceConfig {

    /**
     * Get current user name for saving data to database.
     *
     * @return Usenname
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")
                        ? "SYSTEM"
                        : SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
