package com.hyuse.chatbot.auth.service.impl;

import com.hyuse.chatbot.auth.controller.AuthController;
import com.hyuse.chatbot.auth.dto.AuthRequestDTO;
import com.hyuse.chatbot.auth.dto.AuthResponseDTO;
import com.hyuse.chatbot.auth.dto.UserCreationMessage;
import com.hyuse.chatbot.auth.service.AuthServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServiceInterface {

    private final JwtServiceImpl jwtServiceImpl;
    private final AuthenticationManager authManager;
    private final UserCreationProducerImpl producer;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(JwtServiceImpl jwtServiceImpl, AuthenticationManager authManager, UserCreationProducerImpl producer) {
        this.jwtServiceImpl = jwtServiceImpl;
        this.authManager = authManager;
        this.producer = producer;
    }

    @Override
    public void register(AuthRequestDTO request){

        UserCreationMessage message = new UserCreationMessage();

        message.setEmail(request.email());
        message.setPassword(request.password());

        producer.send(message);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> login(AuthRequestDTO request) {

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtServiceImpl.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponseDTO(token));

        } catch (AuthenticationException e) {
            logger.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new AuthResponseDTO("Invalid credentials"));
        }
    }
}
