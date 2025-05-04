package com.hyuse.chatbot.chat.controller;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.model.MessageType;
import com.hyuse.chatbot.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatMessage> chat(@RequestBody ChatMessage message) {
        try {
            if (message == null || message.getMessage() == null || message.getMessage().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorMessage("A mensagem não pode estar vazia"));
            }

            message.setType(MessageType.USER_MESSAGE);

            ChatMessage response = chatService.processMessage(message);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ChatMessage errorMessage = createErrorMessage("Erro ao processar a mensagem: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    private ChatMessage createErrorMessage(String errorText) {
        return ChatMessage.builder()
                .userId("Bot")
                .message(errorText)
                .type(MessageType.ERROR)
                .isError(true)
                .build();
    }
}

