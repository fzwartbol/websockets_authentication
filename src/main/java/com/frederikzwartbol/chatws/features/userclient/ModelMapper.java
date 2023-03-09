package com.frederikzwartbol.chatws.features.userclient;



import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.BeanUtils;

/** Mapper class for mapping users to Keycloak UserRepresentation and vice versa. */
public class ModelMapper {

    public static KeycloakUser representationToEntity(UserRepresentation userRepresentation) {
        KeycloakUser keycloakUser = new KeycloakUser();
        BeanUtils.copyProperties(userRepresentation, keycloakUser);
        return keycloakUser;
    }

    public static UserRepresentation entityToRepresentation(KeycloakUser keycloakUser) {
        UserRepresentation userRepresentation = new UserRepresentation();
        BeanUtils.copyProperties(keycloakUser, userRepresentation);
        return userRepresentation;
    }

    public static KeycloakUser dtoToEntity(KeycloakUserDTO keycloakUserDTO) {
        KeycloakUser keycloakUser = new KeycloakUser();
        BeanUtils.copyProperties(keycloakUserDTO, keycloakUser);
        return keycloakUser;
    }

    public static KeycloakUserDTO entityToDto(KeycloakUser keycloakUser) {
        KeycloakUserDTO keycloakUserDTO = new KeycloakUserDTO();
        BeanUtils.copyProperties(keycloakUser, keycloakUserDTO);
        return keycloakUserDTO;
    }

//    public static UserRepresentation requestToRepresentation(UserRequest userRequest) {
//        UserRepresentation userRepresentation = new UserRepresentation();
//        BeanUtils.copyProperties(userRequest, userRepresentation);
//        return userRepresentation;
//    }

}
