package com.frederikzwartbol.chatws.features.websocket;

import com.frederikzwartbol.chatws.features.authentication.JWTAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/** This class determines the pricipal from the original GET request and then sets it as Pricipal in the new websocket session security context.
 * A handshake handler is activated when the handshake occurs after a successful http request to a websocket endpoint happens,
 * it then upgrades the connection from http to a websocket.This is a new request so the security context is not saved.
 * see: https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API/Writing_WebSocket_servers
 */
@Slf4j
public class UserHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        var user =  (JWTAuthenticationToken) request.getPrincipal();
        return user != null ? ((Principal) user.getPrincipal()) : null;
    }
}
