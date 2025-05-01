package com.hyuse.chatbot.user.model.dto;

import java.time.LocalDateTime;

public record UserDTO(

        String username,

        String emailAddress,

        String passwordValue,

        LocalDateTime create_at
) {
}