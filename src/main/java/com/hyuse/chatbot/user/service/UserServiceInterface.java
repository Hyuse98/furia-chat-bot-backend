package com.hyuse.chatbot.user.service;

import com.hyuse.chatbot.user.model.dto.UserDTO;
import com.hyuse.chatbot.user.model.User;

import java.util.Collection;
import java.util.UUID;

public interface UserServiceInterface {

    User createUser(String username ,String email, String rawPassword);
    User updateUser(UUID id, UserDTO userDTO);

    User findUserById(UUID id);
    User findUserByUsername(String username);
    User findUserByEmail(String emailAddress);
    Collection<User> findAllUsers();

    void deleteUser(UUID id);
}
