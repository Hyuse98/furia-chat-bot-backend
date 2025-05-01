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
        entityModel.add(linkTo(methodOn(PlayerController.class).listPlayers(Pageable.unpaged())).withRel("players"));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EntityModel<Player>> getPlayerByName(@PathVariable String name) {
        Player player = playerService.getPlayerByName(name);
        EntityModel<Player> entityModel = EntityModel.of(player);
        entityModel.add(linkTo(methodOn(PlayerController.class).getPlayerByName(name)).withSelfRel());
        entityModel.add(linkTo(methodOn(PlayerController.class).listPlayers(Pageable.unpaged())).withRel("players"));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Player>>> listPlayers(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<Player> playersPage = playerService.listPlayers(pageable);

        List<EntityModel<Player>> playerModels = playersPage.getContent().stream()
                .map(player -> EntityModel.of(player,
                        linkTo(methodOn(PlayerController.class).getPlayerById(player.getId())).withSelfRel()))
                .collect(Collectors.toList());

        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(playersPage.getSize(), playersPage.getNumber(), playersPage.getTotalElements(), playersPage.getTotalPages());

        PagedModel<EntityModel<Player>> pagedModel = PagedModel.of(playerModels, metadata,
                linkTo(methodOn(PlayerController.class).listPlayers(pageable)).withSelfRel());

        return ResponseEntity.ok(pagedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
