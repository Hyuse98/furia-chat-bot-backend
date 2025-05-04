package com.hyuse.chatbot.player.controller;

import com.hyuse.chatbot.player.model.dto.PlayerDTO;
import com.hyuse.chatbot.player.model.Player;
import com.hyuse.chatbot.player.service.PlayerServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerServiceInterface playerService;

    @Autowired
    public PlayerController(PlayerServiceInterface playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Player>> createPlayer(@RequestBody @Valid PlayerDTO playerDTO) {
        playerService.createPlayer(playerDTO);
        Player player = playerService.getPlayerByName(playerDTO.name());
        EntityModel<Player> entityModel = EntityModel.of(player);
        entityModel.add(linkTo(methodOn(PlayerController.class).getPlayerById(player.getId())).withSelfRel());
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Player>> updatePlayer(@PathVariable Long id, @RequestBody @Valid PlayerDTO playerDTO) {
        playerService.updatePlayer(playerDTO, id);
        Player player = playerService.getPlayerById(id);
        EntityModel<Player> entityModel = EntityModel.of(player);
        entityModel.add(linkTo(methodOn(PlayerController.class).getPlayerById(id)).withSelfRel());
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Player>> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        EntityModel<Player> entityModel = EntityModel.of(player);
        entityModel.add(linkTo(methodOn(PlayerController.class).getPlayerById(id)).withSelfRel());
//        entityModel.add(linkTo(methodOn(PlayerController.class).listPlayers()).withRel("players"));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EntityModel<Player>> getPlayerByName(@PathVariable String name) {
        Player player = playerService.getPlayerByName(name);
        EntityModel<Player> entityModel = EntityModel.of(player);
        entityModel.add(linkTo(methodOn(PlayerController.class).getPlayerByName(name)).withSelfRel());
//        entityModel.add(linkTo(methodOn(PlayerController.class).listPlayers()).withRel("players"));
        return ResponseEntity.ok(entityModel);
    }

//    @GetMapping
//    public ResponseEntity<EntityModel<Player>> listPlayers(@PathVariable String team) {
//        List<Player> players = playerService.listPlayers(team);
//
//        EntityModel<Player> playerModels = players.stream()
//                .map(player -> EntityModel.of(player,
//                        linkTo(methodOn(PlayerController.class).getPlayerById(player.getId())).withSelfRel()))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(playerModels);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
