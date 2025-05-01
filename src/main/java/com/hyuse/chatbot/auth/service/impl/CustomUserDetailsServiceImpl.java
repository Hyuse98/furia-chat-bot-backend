package com.hyuse.chatbot.auth.service.impl;

import com.hyuse.chatbot.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(user -> {
                    String encodedPassword = user.getPassword();
                    logger.debug("Encoded password from database: {}", encodedPassword);
                    return User.withUsername(user.getUsername())
                            .password(encodedPassword) //ByCrypt SHA-256
                            .authorities("USER")
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
