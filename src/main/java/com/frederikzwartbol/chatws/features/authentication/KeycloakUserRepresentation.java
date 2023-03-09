package com.frederikzwartbol.chatws.features.authentication;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Keycloak user representation which is mapped from the base64 decoded JWT access token presented in header.
 */
@AllArgsConstructor
public class KeycloakUserRepresentation {
        @Getter
        String sub;
        @Getter
        String preferred_username;
        @Getter
        Set<String> roles;

        public static KeycloakUserRepresentation fromDecodedJWT(DecodedJWT decodedJWT) {
                return new KeycloakUserRepresentation(
                        decodedJWT.getClaims().get("sub").asString(),
                        decodedJWT.getClaims().get("preferred_username").asString(),
                        Set.of("ROLES_USER"));
        }

}
