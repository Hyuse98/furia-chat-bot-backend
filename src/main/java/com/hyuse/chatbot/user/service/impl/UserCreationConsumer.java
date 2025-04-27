package com.hyuse.chatbot.user.service.impl;

import com.hyuse.chatbot.auth.dto.UserCreationMessage;
import com.hyuse.chatbot.user.service.UserServiceInterface;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserCreationConsumer {

    private final UserServiceInterface userService;

    public UserCreationConsumer(UserServiceInterface userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "auth.user.create.queue")
    public void consume(UserCreationMessage message) {
        System.out.println("Mensagem recebida: " + message);
        userService.createUser(message.getEmail(), message.getPassword());
    }
}

