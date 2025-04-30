package com.hyuse.chatbot.chat.service;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.model.MessageType;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public ChatMessage processMessage(ChatMessage input) {
        String content = input.getMessage().trim().toLowerCase();

        if (content.startsWith("/")) {
            return handleCommand(content.substring(1), input.getUserId());
        }

        return ChatMessage.builder()
                .userId("bot")
                .message("Não entendi. Digite /help para ver os comandos.")
                .type(MessageType.BOT_RESPONSE)
                .build();
    }

    private ChatMessage handleCommand(String command, String userId) {
        return switch (command) {
            case "start" -> reply(userId, "Bem-vindo ao chatbot!");
            case "help" -> reply(userId, "Comandos disponíveis: /start, /help, /info");
            case "info" -> reply(userId, "Este é um chatbot simples em Spring Boot.");
            default -> reply(userId, "Comando não reconhecido.");
        };
    }

    private ChatMessage reply(String userId, String message) {
        return ChatMessage.builder()
                .userId("bot")
                .message(message)
                .type(MessageType.BOT_RESPONSE)
                .build();
    }
}
