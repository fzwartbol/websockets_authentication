package com.frederikzwartbol.chatws.features.authentication;

import com.frederikzwartbol.chatws.features.authentication.filters.JWTAuthenticationFilter;
import com.frederikzwartbol.chatws.features.authentication.filters.WSAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Websecurity configuration for Spring Security
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${keycloak.client.public-key}")
    private String keycloakClientPublicKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .cors( cors -> cors.disable())
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/**")
//                    .authenticated()
                .permitAll()
                .and()
                .httpBasic();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(wsAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.headers().frameOptions().disable().httpStrictTransportSecurity().disable();

        return http.build();
    }

    /**
     * Bean method for the authentication manager which uses a custom provider to validate JWT tokens.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .authenticationProvider(new JWTAuthenticationProvider(tokenVerifier()));
        return authenticationManagerBuilder.build();
    }

    /**
     * Bean for custom JWT token verifier
     * @return
     */
    @Bean
    public KeycloakVerifier tokenVerifier() {
        return new KeycloakVerifier(keycloakClientPublicKey);
    }

    /**
     * Custom filter beans for in the filter chain
     * @return
     */
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public WSAuthenticationFilter wsAuthenticationFilter() {return new WSAuthenticationFilter();}

}
