package com.frederikzwartbol.chatws.features.userclient.adminconsumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.List;


/** Implementation of Keycloak Consumer for connecting to the Keycloak Admin API. */
@Component
@RequiredArgsConstructor
@Slf4j
public class IAdminRealmUserConsumer implements AdminRealmUserConsumer{

    private final Keycloak keycloak;
    public String realm = "chatapp";

    @Override
    public List<UserRepresentation> getAllUsers() {
        return getRealmResource().users().list().stream().toList();
    }

    @Override
    public boolean checkUserIdExists(String id) {
        return getRealmResource().users().get(id) != null;
    }

    @Override
    public UserRepresentation findUserById(String id) {
        return getRealmResource().users().get(id).toRepresentation();
    }

    @Override
    public UserRepresentation findUserByUsername(String username) {
        return getRealmResource().users().search(username).get(0);
    }

    @Override
    public Response saveUser(UserRepresentation user) {
        return getRealmResource().users().create(user);
    }

    @Override
    public void deleteUser(String id) {
        getRealmResource().users().delete(id);
    }

    @Override
    public RealmResource getRealmResource() {
        keycloak.tokenManager().getAccessToken();
        return keycloak.realm( realm);
    }
}

