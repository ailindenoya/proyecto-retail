package com.proyectoaccenture.demo.ai;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> chatPrompt(@RequestBody Map<String, String> message) {
        String userMessage = message.get("message");

        if (userMessage != null) {
            String aiResponse = chatClient
                    .prompt(userMessage)
                    .call()
                    .content();

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("response", aiResponse);

            return ResponseEntity.ok(responseMap);
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Mensaje no válido."));
        }
    }
}
