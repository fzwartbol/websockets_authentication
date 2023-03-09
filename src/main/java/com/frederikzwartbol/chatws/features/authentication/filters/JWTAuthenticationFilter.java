package com.frederikzwartbol.chatws.features.authentication.filters;

import com.frederikzwartbol.chatws.features.authentication.JWTAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom JWT Authentication filter for parsing a JSON Web Token (JWT) from the "Authorization" header of a request,
 * it then passes the token to the authentication manager for validation and sets the security context.
 * @Author Frederik Zwartbol
 */
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Custom JWTAuthenticationFilter is triggered for request {}",request);

        String parsedToken = getJWTFromRequest(request);

        if(parsedToken == null) {
            filterChain.doFilter(request,response);
            return;
        }

        var authRequest = JWTAuthenticationToken.unauthenticated(parsedToken);

        try {
            var authentication = authenticationManager.authenticate(authRequest);
            var newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(newContext);
            filterChain.doFilter(request,response);

        } catch (AuthenticationException | ServletException e) {
            throw new BadCredentialsException("Authentication exception");
        }
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
