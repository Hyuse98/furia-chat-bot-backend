package com.hyuse.chatbot.auth.service.impl;

import com.hyuse.chatbot.auth.dto.UserCreationMessage;
import com.hyuse.chatbot.auth.service.UserCreationProducerInterface;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserCreationProducerImpl implements UserCreationProducerInterface {

    private final RabbitTemplate rabbitTemplate;

    public UserCreationProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(UserCreationMessage message) {
        rabbitTemplate.convertAndSend("auth.user.exchange", "user.create", message);
    }
}

