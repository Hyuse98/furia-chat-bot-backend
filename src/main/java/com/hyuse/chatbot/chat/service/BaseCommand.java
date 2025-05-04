package com.hyuse.chatbot.chat.service;

import com.hyuse.chatbot.chat.model.ChatMessage;
import com.hyuse.chatbot.chat.model.MessageType;

public abstract class BaseCommand implements CommandHandler {

    private static final String BOT_ID = "Assistant";

    /**
     * Cria uma resposta do bot padronizada
     * @param message A mensagem de resposta
     * @return ChatMessage com a resposta formatada
     */
    protected ChatMessage createResponse(String message) {
        return ChatMessage.builder()
                .userId(BOT_ID)
                .message(message)
                .type(MessageType.BOT_RESPONSE)
                .build();
    }

    /**
     * Cria uma resposta de erro padronizada
     * @param message A mensagem de erro
     * @return ChatMessage com o erro formatado
     */
    protected ChatMessage createErrorResponse(String message) {
        return ChatMessage.builder()
                .userId(BOT_ID)
                .message("Erro: " + message)
                .type(MessageType.BOT_RESPONSE)
                .build();
    }
}
