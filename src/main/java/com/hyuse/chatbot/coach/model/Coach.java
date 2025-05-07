package com.hyuse.chatbot.coach.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coaches")
public class Coach {

    public Coach() {
    }

    public Coach(String name, String username, String team, String hltvUrl) {
        this.name = name;
        this.username = username;
        this.team = team;
        this.hltvUrl = hltvUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    private String team;

    private String hltvUrl;
}
