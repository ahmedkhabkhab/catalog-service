package com.bookshop.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJdbcAuditing
public class DataConfig {
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext()) // Get the SecurityContext object of the currently authenticated user
                .map(SecurityContext::getAuthentication) // Extract Authentication object
                .filter(Authentication::isAuthenticated) // Handle the case where the user is not authenticated
                .map(Authentication::getName); // Extract the username
    }
}
