package com.frederikzwartbol.chatws.features.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Custom Authentication object for a JWT Authentication token, which can be created/validated by
 * a custom authentication provider within the authentication Manager.
 * This class is validated by the JWT Authentication Provider.
 */
public class JWTAuthenticationToken extends AbstractAuthenticationToken{

    private static final long serialVersionUID = 1L;
    private String token;
    private User principal;

    /**
     * Static factory method for initializing an unauthenticated authentication object
     * @param token
     * @return
     */
    public static JWTAuthenticationToken unauthenticated(String token) {
        return authenticated(token, null, null);
    }

    /**
     * Static factory method for initializing an authenticated authentication object
     * @param token
     * @return
     */
    public static  JWTAuthenticationToken authenticated(String token, User principal, Collection<GrantedAuthority> authorities) {
        return new JWTAuthenticationToken(token,principal,authorities);
    }

    public JWTAuthenticationToken(String token, User principal ,Collection<? extends GrantedAuthority> authorities ) {
        super(authorities);
        this.token = token;
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public String toString() {
        return "JWTAuthenticationToken{" +
                "token='" + token + '\'' +
                ", principal=" + principal +
                '}';
    }
}
