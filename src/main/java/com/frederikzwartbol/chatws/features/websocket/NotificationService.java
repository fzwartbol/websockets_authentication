package com.frederikzwartbol.chatws.features.websocket;

import com.frederikzwartbol.chatws.features.conversation.message.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate  messagingTemplate;

    public void sendPrivateNotification(final String userId) {
        MessageResponseDto message = MessageResponseDto.builder()
                .messageContent("notification")
                .build();

       messagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications",message);
    }
}
