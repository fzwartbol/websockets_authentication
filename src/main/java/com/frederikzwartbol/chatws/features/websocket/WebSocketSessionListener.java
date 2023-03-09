package com.frederikzwartbol.chatws.features.websocket;

import com.frederikzwartbol.chatws.features.authentication.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketSessionListener implements ApplicationListener<SessionConnectedEvent>{
    private final Map<String, String> sessionUserMap = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        log.debug("Session event triggered {}", event);
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        log.debug("event session"+sessionId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug("authenticated object {}",auth);
        if (auth != null && auth.getPrincipal() instanceof User) {
            String username = ( ((User) auth.getPrincipal()).getUsername());
            String id=  ( ((User) auth.getPrincipal()).getId());
            log.debug("Username = {} userid = {}",username,id);
            sessionUserMap.put(sessionId, username);
            log.debug( username + " connected with session ID " + sessionId);
        }
    }


}

