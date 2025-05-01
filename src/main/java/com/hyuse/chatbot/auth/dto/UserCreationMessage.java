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
     * @param username User username
     * @param email User Email
     * @param password User Password Raw
     */

    private String username;
    private String email;
    private String password;

    public UserCreationMessage(String username ,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserCreationMessage() {
    }
}
