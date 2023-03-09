package com.frederikzwartbol.chatws.features.conversation.message;


import com.frederikzwartbol.chatws.features.authentication.User;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true, fluent = true)
public class Message {
    private String id;
    private String messageContent;
    private LocalDateTime sendDate;
    private String conversationId;
    private User user;
}
