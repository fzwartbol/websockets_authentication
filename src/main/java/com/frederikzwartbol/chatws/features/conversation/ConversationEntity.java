package com.frederikzwartbol.chatws.features.conversation;

import com.frederikzwartbol.chatws.features.conversation.message.Message;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document
@Builder
public class ConversationEntity {

    @Id
    @Getter
    private  String id;

    @Getter
    private List<Message> messages;

    @Getter
    private Set<String> participants;
}
