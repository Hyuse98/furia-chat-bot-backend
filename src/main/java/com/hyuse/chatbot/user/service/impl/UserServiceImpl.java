package com.hyuse.chatbot.user.service.impl;

import com.hyuse.chatbot.user.dto.UserDTO;
import com.hyuse.chatbot.exceptions.UserAlreadyExistsException;
import com.hyuse.chatbot.exceptions.UserNotFoundException;
import com.hyuse.chatbot.user.model.User;
import com.hyuse.chatbot.user.repository.UserRepository;
import com.hyuse.chatbot.user.service.UserServiceInterface;
import com.hyuse.chatbot.user.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User createUser(String email, String rawPassword) {

        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com o email: " + email);
        }

        var password = passwordEncoder.encode(rawPassword);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setCreateAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com id: " + id));
    }

    //TODO Tratar Upper e Lower Case
    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com nome: " + username));
    }

    //TODO Validar Formato do Email
    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String emailAddress) {
        return userRepository.findByEmail(emailAddress)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com email: " + emailAddress));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Usuário não encontrado com id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User updateUser(UUID id, UserDTO userDTO) {
        User user = findUserById(id);

        if (!user.getEmail().equals(userDTO.emailAddress())) {
            validateEmailNotExists(userDTO.emailAddress());
            user.setEmail(userDTO.emailAddress());
        }

        updateUserData(user, userDTO);
        return userRepository.save(user);
    }

    private void validateEmailNotExists(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Já existe um usuário com o email: " + email);
        }
    }

    private void updateUserData(User user, UserDTO userDTO) {
        user.setUsername(userDTO.username());
    }
}
