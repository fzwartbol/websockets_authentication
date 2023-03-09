package com.frederikzwartbol.chatws.features.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static com.frederikzwartbol.chatws.features.websocket.WebsocketConstants.WEBSOCKET_ENDPOINT;


@Configuration
@EnableWebSocketMessageBroker
@Slf4j
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * Registers endpoint for opening a websocket on the backend
     * which supports the STOMP protocol (Simple Text Orientated Messaging Protocol)
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(WEBSOCKET_ENDPOINT)
                .setHandshakeHandler(new UserHandshakeHandler())
                .setAllowedOrigins("http://localhost:3000");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * Endpoint broker for client to subscribe to a topic ( after connecting to a websocket)
         */
        registry.enableSimpleBroker("/topic");

        /**
         * Prefix for message broker
         */
        registry.setApplicationDestinationPrefixes("/ws");

        registry.setUserDestinationPrefix("/user");
    }

}
