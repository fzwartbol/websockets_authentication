package com.frederikzwartbol.chatws.features.userclient;


import lombok.Data;

@Data
public class KeycloakUser {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
}
