package com.frederikzwartbol.chatws.features.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @RequestMapping(value = "/")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
