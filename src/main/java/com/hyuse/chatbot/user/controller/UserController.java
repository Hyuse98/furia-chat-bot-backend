package com.hyuse.chatbot.user.controller;

import com.hyuse.chatbot.user.dto.UserDTO;
import com.hyuse.chatbot.user.model.User;
import com.hyuse.chatbot.user.service.impl.UserServiceImpl;
import com.hyuse.chatbot.user.util.UserAssembler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;
    private final UserAssembler userAssembler;

    @Autowired
    public UserController(UserServiceImpl userService, UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable UUID id) {

        var user = userService.findUserById(id);
        EntityModel<User> entityModel = userAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/name/{fullName}")
    public ResponseEntity<EntityModel<User>> getUserByUsername(@PathVariable String username) {

        User user = userService.findUserByUsername(username);
        EntityModel<User> entityModel = userAssembler.toModel(user);

        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/email/{emailAddress}")
    public ResponseEntity<EntityModel<User>> getUserByEmail(@PathVariable String emailAddress) {

        var user = userService.findUserByEmail(emailAddress);
        EntityModel<User> entityModel = userAssembler.toModel(user);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers() {

        Collection<User> users = userService.findAllUsers();
        Collection<EntityModel<User>> userEntityModels = users.stream()
                .map(userAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<User>> collectionModel = CollectionModel.of(userEntityModels,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable UUID id, @RequestBody @Valid UserDTO userDTO) {

        var updatedUser = userService.updateUser(id, userDTO);
        EntityModel<User> entityModel = userAssembler.toModel(updatedUser);
        return ResponseEntity.ok(entityModel);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
