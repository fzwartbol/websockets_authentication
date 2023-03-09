package com.frederikzwartbol.chatws.features.conversation.message;

import lombok.Data;

@Data
public class MessageDto {
    private String messageContent;
    private String conversationId;
}
