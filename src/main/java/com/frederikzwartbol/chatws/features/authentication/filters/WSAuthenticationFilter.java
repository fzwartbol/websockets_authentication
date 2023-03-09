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
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom WS Authentication filter for parsing a JSON Web Token (JWT) from the requestParameter the request,
 * it then passes the token to the authentication manager for validation and sets the security context.
 * @Author Frederik Zwartbol
 */
@Slf4j
public class WSAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!request.getRequestURI().equals("/ws")) {
            filterChain.doFilter(request,response);
        }

        log.debug("Custom WSAuthenticationFilter is triggered for request {}",request);

        String token = request.getParameter("access_token");

        if(token == null) {
            filterChain.doFilter(request,response);
            return;
        }

        var authRequest = JWTAuthenticationToken.unauthenticated(token);
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
}
