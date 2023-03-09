package com.frederikzwartbol.chatws.features.conversation.message;

import com.frederikzwartbol.chatws.features.websocket.NotificationService;
import com.frederikzwartbol.chatws.features.websocket.WSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final WSService wsService;
    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * @MessageMapping = Receiving endpoint of messages in websocket
     * @SendTo = Publishing endpoint of message in websocket
     */
    @MessageMapping("/message")
    public void getMessage(final MessageDto messageDto){
        wsService.sendToAllRoute(
                MessageMapper.DtoToMessage(messageDto));
    }

    @MessageMapping("/private-message")
    public void getPrivateMessage(final MessageDto messageDto){
    wsService.sendMessageToConversationParticipants(messageDto);
    }


}
