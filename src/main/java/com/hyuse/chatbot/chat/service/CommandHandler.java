package com.hyuse.chatbot.chat.service;

import com.hyuse.chatbot.chat.model.ChatMessage;

public interface CommandHandler {
    /**
     * Verifica se este handler suporta o comando fornecido
     * @param command O comando (sem o caractere /)
     * @return true se o handler suporta este comando
     */
    boolean supports(String command);

    /**
     * Executa o comando
     * @param userId ID do usuário que enviou o comando
     * @return A resposta do bot
     */
    ChatMessage handle(String userId);

    /**
     * Retorna a descrição de ajuda para este comando
     * @return String com descrição do comando e seu uso
     */
    default String getHelpText() {
        return "Nenhuma ajuda disponível para este comando.";
    }
}

