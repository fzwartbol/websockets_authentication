package com.frederikzwartbol.chatws.features.conversation.message;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponseDto {
    private String id;
    private String messageContent;
    private LocalDateTime sendDate;
    private String conversationId;
}
