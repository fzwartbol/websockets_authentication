package com.frederikzwartbol.chatws.features.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This provider class validates authentication objects of type JWTAuthenticationToken.
 * @Author Frederik Zwartbol
 */
@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final KeycloakVerifier keycloakVerifier;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("Authenticating authentication object of type JWTAuthenticationToken.class with authentication provider {}", JWTAuthenticationProvider.class);

        JWTAuthenticationToken token = (JWTAuthenticationToken) authentication;
        String tokenString = (String) token.getCredentials();

        try {
            KeycloakUserRepresentation accessToken = keycloakVerifier.verifyToken(tokenString);
            List<GrantedAuthority> authorities = accessToken.getRoles().stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            User user = new User(accessToken.getSub(), accessToken.getPreferred_username(), "naame","asdfasdf","link");

            token = JWTAuthenticationToken.authenticated(tokenString, user, authorities);
            token.setAuthenticated(true);
        } catch (AuthenticationException e) {
            log.debug("Exception authenticating the token {}:", tokenString, e);
            throw new BadCredentialsException("Invalid token");
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
