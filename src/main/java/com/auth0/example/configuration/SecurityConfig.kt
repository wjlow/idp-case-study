package com.auth0.example.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.web.SecurityFilterChain


/**
 * Configures our application with Spring Security to restrict access to our API endpoints.
 */
@Configuration
open class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/api/interviews"
                ).hasAuthority("SCOPE_read:interviews")
            }
            .cors(Customizer.withDefaults())
            .oauth2ResourceServer { it.jwt(Customizer.withDefaults()) }
            .build()
    }
}
