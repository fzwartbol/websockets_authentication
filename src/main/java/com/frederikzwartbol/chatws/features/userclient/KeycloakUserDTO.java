package com.frederikzwartbol.chatws.features.userclient;


import lombok.Data;

@Data
public class KeycloakUserDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
}
