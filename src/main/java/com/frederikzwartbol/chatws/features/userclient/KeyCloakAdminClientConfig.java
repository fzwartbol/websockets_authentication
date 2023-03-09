package com.frederikzwartbol.chatws.features.userclient;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/** Admin client keycloak configuration class */
@Configuration
@ComponentScan
public class KeyCloakAdminClientConfig {
    @Value("${keycloak.admin.auth-server-url}")
    public String SERVER_URL;
    @Value("${keycloak.admin.realm}")
    public String REALM;
    @Value("${keycloak.admin.client-id}")
    public String CLIENT_ID;
    @Value("${keycloak.admin.credentials.username}")
    public String USERNAME;
    @Value("${keycloak.admin.credentials.password}")
    public String PASSWORD;

    @Bean
    public Keycloak getKeycloak() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(REALM)
                .clientId(CLIENT_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        return keycloak;
    }

}
