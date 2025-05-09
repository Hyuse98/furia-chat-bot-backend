package com.hyuse.chatbot.user.enums;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MODERATOR;

    public String getName() {
        return name().substring("ROLE_".length());
    }
}
