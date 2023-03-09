package com.frederikzwartbol.chatws.features.conversation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping
    ResponseEntity<ConversationDto> startConversation(@RequestBody ConversationInitRequest conversationInitRequest) {
        log.info(conversationInitRequest.userIds().toString());
        return ResponseEntity.ok(
                ConversationMapper.domainToDto(
                conversationService.startConversation(
                        conversationInitRequest.userIds())));
    }

}
