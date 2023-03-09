package com.frederikzwartbol.chatws.features.websocket;

import com.frederikzwartbol.chatws.features.conversation.ConversationService;
import com.frederikzwartbol.chatws.features.conversation.message.Message;
import com.frederikzwartbol.chatws.features.conversation.message.MessageDto;
import com.frederikzwartbol.chatws.features.conversation.message.MessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WSService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ConversationService conversationService;

    public void sendToAllRoute(final Message message) {
        messagingTemplate.convertAndSend("/topic/messages", MessageMapper.MessageToResponseDto(message));
    }

    public void sendMessageToConversationParticipants(MessageDto message){
        log.debug("Send message to conversation participants");
        var conv = conversationService.findConversationById(message.getConversationId());
        conv.participants().forEach( user ->
                messagingTemplate.convertAndSendToUser(user,"/topic/private-messages", message));
    }
}
