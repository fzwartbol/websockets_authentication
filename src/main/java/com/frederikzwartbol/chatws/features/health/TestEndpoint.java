package com.frederikzwartbol.chatws.features.health;

import com.frederikzwartbol.chatws.features.conversation.message.MessageMapper;
import com.frederikzwartbol.chatws.features.userclient.adminconsumer.IAdminRealmUserConsumer;
import com.frederikzwartbol.chatws.features.conversation.message.MessageDto;
import com.frederikzwartbol.chatws.features.websocket.WSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestEndpoint {

    private final IAdminRealmUserConsumer adminRealmUserConsumer;

    private final WSService wsService;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<?> postMessage(@RequestBody final MessageDto messageDto) {
        wsService.sendToAllRoute(MessageMapper.DtoToMessage(messageDto));
        return ResponseEntity.ok("");
    }

    @PostMapping("/privatemessage")
    public void getPrivateMessage(final MessageDto messageDto){
        wsService.sendMessageToConversationParticipants(messageDto);
    }


    @GetMapping
    ResponseEntity<?> getObjects() {
        return ResponseEntity.ok(adminRealmUserConsumer.getAllUsers());
    }
}
