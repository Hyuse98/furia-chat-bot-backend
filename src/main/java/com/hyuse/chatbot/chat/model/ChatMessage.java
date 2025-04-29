package com.hyuse.chatbot.chat.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String userId;
    private String message;
    private MessageType type; // USER_MESSAGE, BOT_RESPONSE, COMMAND
}

