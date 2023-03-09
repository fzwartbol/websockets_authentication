package com.frederikzwartbol.chatws.features.conversation;

import com.frederikzwartbol.chatws.features.conversation.message.Message;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record ConversationDto(
        String id,
        List<Message> messages,
        Set<String> participants
) {
}
