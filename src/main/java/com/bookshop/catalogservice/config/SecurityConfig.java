package com.bookshop.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Enable Spring MVC support for Spring Security
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .mvcMatchers(HttpMethod.GET, "/", "/books/**")
                        .permitAll() // Allow fetching books (GET) without authentication
                        .anyRequest().authenticated())// Any other request should be authenticated
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // Enable OAuth2 Resource server support using JWT authentication
                .sessionManagement(sessionManagement ->  sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Each request needs to include the access token. Stateless behavior and no need to keep the user session alive between requests
                .csrf(AbstractHttpConfigurer::disable) // Stateless authentication --> No need to use CSRF protection
                .build();
    }

}
