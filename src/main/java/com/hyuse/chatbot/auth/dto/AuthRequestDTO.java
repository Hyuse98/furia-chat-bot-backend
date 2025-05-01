package com.hyuse.chatbot.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthRequestDTO(

        @NotBlank
        String username,

        @Email
        String email,

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "Password must have at least 8 characters, contains one number, contains lowercase char, contains uppercase char and a special char.")
        String password
) {
}