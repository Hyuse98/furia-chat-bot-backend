package com.hyuse.chatbot.auth.service;

import com.hyuse.chatbot.auth.dto.AuthRequestDTO;
import com.hyuse.chatbot.auth.dto.AuthResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthServiceInterface {

    void register(AuthRequestDTO request);
    ResponseEntity<AuthResponseDTO> login(AuthRequestDTO request);
}
