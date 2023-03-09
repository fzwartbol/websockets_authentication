package com.frederikzwartbol.chatws.features.conversation;

import com.frederikzwartbol.chatws.features.conversation.message.Message;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true, fluent = true)
@Slf4j
public class Conversation {
    private String id;
    private List<Message> messages;
    private Set<String> participants;

    public void sendMessage (Message message) {
        message.conversationId(id);
        messages.add(message);
    }

    public static Conversation startConversation(Set<String> participants) {
        var conversation = new Conversation(new ArrayList<>(),participants);
        log.info(conversation.toString());
        return conversation;
    }


    public Conversation(List<Message> messages, Set<String> participants) {
        this.messages = messages;
        this.participants = participants;
    }
    public Conversation() {
    }

}
