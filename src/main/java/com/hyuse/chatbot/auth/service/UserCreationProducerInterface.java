package com.hyuse.chatbot.auth.service;

import com.hyuse.chatbot.auth.dto.UserCreationMessage;

public interface UserCreationProducerInterface {

    void send(UserCreationMessage message);
}
