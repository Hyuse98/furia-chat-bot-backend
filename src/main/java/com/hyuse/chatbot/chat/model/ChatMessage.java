package com.hyuse.chatbot.chat.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String userId;
    private String message;
    private MessageType type;
    private boolean IsHtml;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String commandName;
    private boolean isError;
    private Object additionalData;
}