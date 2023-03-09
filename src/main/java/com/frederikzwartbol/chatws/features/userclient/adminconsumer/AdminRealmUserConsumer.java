package com.frederikzwartbol.chatws.features.userclient.adminconsumer;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.List;

public interface AdminRealmUserConsumer {
    List<UserRepresentation> getAllUsers();

    boolean checkUserIdExists(String id);
    UserRepresentation findUserById(String id);
    UserRepresentation findUserByUsername(String username);
    Response saveUser(UserRepresentation user);
    void deleteUser(String id);
    RealmResource getRealmResource();
}
