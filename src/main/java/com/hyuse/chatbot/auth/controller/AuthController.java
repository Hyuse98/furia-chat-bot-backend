package com.hyuse.chatbot.auth.controller;
import com.hyuse.chatbot.auth.dto.AuthRequestDTO;
import com.hyuse.chatbot.auth.dto.AuthResponseDTO;
import com.hyuse.chatbot.auth.service.AuthServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceInterface authService;

    @Autowired
    public AuthController(AuthServiceInterface authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AuthRequestDTO request) {

        authService.register(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário em processo de registro");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request) {

        return authService.login(request);
    }

}

