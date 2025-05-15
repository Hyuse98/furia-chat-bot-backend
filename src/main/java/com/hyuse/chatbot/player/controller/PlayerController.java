package com.hyuse.chatbot.player.controller;

import com.hyuse.chatbot.player.model.dto.PlayerDTO;
import com.hyuse.chatbot.player.model.Player;
import com.hyuse.chatbot.player.service.PlayerService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        try {
            PlayerDTO createdPlayer = playerService.create(playerDTO);
            return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        try {
            PlayerDTO playerDTO = playerService.getById(id);
            return ResponseEntity.ok(playerDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> playerDTOs = playerService.getAll();
        return ResponseEntity.ok(playerDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDTO) {
        try {
            PlayerDTO updatedPlayer = playerService.update(playerDTO, id);
            return ResponseEntity.ok(updatedPlayer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        try {
            playerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<PlayerDTO> getPlayerByUsername(@PathVariable String username) {
        try {
            PlayerDTO playerDTO = playerService.getByUsername(username);
            return ResponseEntity.ok(playerDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlayerDTO> getPlayerByName(@PathVariable String name) {
        try {
            PlayerDTO playerDTO = playerService.getByName(name);
            return ResponseEntity.ok(playerDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/team/{team}")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(@PathVariable String team) {
        try {
            List<PlayerDTO> playerDTOs = playerService.getByTeam(team);
            return ResponseEntity.ok(playerDTOs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}