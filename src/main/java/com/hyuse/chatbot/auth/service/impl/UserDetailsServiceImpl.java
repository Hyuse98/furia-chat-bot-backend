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
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(user -> {
                    String encodedPassword = user.getPassword();
                    return User.withUsername(user.getUsername())
                            .password(encodedPassword) //ByCrypt SHA-256
                            .authorities("USER")
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
