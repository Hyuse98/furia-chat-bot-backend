package com.hyuse.chatbot.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServiceInterface {

    String generateToken(String email);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

}
