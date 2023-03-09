package com.frederikzwartbol.chatws.features.conversation;

import com.frederikzwartbol.chatws.features.exceptions.GenericNotFoundException;
import com.frederikzwartbol.chatws.features.userclient.adminconsumer.AdminRealmUserConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final AdminRealmUserConsumer adminRealmUserConsumer;
    private final ConversationRepository conversationRepository;

    public Conversation startConversation(Set<String> userId) {
        Set<Boolean> usersExits =
                userId
                .stream()
                .map(adminRealmUserConsumer::checkUserIdExists).collect(Collectors.toSet());

        if(!usersExits.contains(false)) {
            var conversation = Conversation.startConversation(userId);
            return ConversationMapper.entityToDomain(saveConversation(conversation));
        } else throw new GenericNotFoundException("User not found");
    }

    public Conversation findConversationById(String id) {
       return conversationRepository
               .findById(id)
               .map(ConversationMapper::entityToDomain)
               .orElseThrow(() ->
                       new GenericNotFoundException("Conversation not found."));
    }

    public ConversationEntity saveConversation (Conversation conversation) {
        return conversationRepository.save(ConversationMapper.domainToEntity(conversation));
    }
}
