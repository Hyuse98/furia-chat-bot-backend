package com.hyuse.chatbot.user.util;

import com.hyuse.chatbot.user.model.dto.UserDTO;
import com.hyuse.chatbot.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDTO userDTO) {

        var username = userDTO.username();
        var email = userDTO.emailAddress();
        var password = userDTO.passwordValue();
        var create_at = userDTO.create_at();

        return new User(username, email, password, create_at);
    }

    public UserDTO toDto(User user) {

        return new UserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getCreateAt()
        );
    }
}