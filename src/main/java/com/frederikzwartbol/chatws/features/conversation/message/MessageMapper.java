package com.frederikzwartbol.chatws.features.conversation.message;

import java.time.LocalDateTime;

public class MessageMapper {

    public static Message DtoToMessage (final MessageDto messageDto) {
        return new Message()
                .conversationId(messageDto.getConversationId())
                .messageContent(messageDto.getMessageContent())
                .sendDate(LocalDateTime.now());
    }

    public static MessageResponseDto MessageToResponseDto (final Message message) {
        return MessageResponseDto.builder()
                .id("1")
                .conversationId(message.conversationId())
                .messageContent(message.messageContent())
                .sendDate(LocalDateTime.now())
                .build();

    }
}
