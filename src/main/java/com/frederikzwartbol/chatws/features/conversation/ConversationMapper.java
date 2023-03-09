package com.frederikzwartbol.chatws.features.conversation;

public class ConversationMapper {

    public static ConversationDto domainToDto(Conversation conversation) {
        return ConversationDto.builder()
                .id(conversation.id())
                .participants(conversation.participants())
                .messages(conversation.messages())
                .build();
    }

    public static ConversationEntity domainToEntity(Conversation conversation) {
        return ConversationEntity.builder()
                .id(conversation.id())
                .participants(conversation.participants())
                .messages(conversation.messages())
                .build();
    }

    public static Conversation entityToDomain (ConversationEntity conversationEntity) {
        return new Conversation()
                .id(conversationEntity.getId())
                .participants(conversationEntity.getParticipants())
                .messages(conversationEntity.getMessages());
    }
}
