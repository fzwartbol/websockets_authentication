package com.frederikzwartbol.chatws.features.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;

@AllArgsConstructor
@Data
public class User implements Principal {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;

    @Override
    public String getName() {
        return username;
    }
}
