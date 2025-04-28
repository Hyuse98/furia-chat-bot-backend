package com.hyuse.chatbot.auth.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreationMessage {

    /**
     * RabbitMQ Message Class Model
     * Used as Model to RabbitMQ Queue
     *
     * @param email User Email
     * @param password User Password Raw
     */

    public UserCreationMessage(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserCreationMessage() {
    }

    private String email;
    private String password;
}
