package com.hyuse.chatbot.team.model;

import com.hyuse.chatbot.player.model.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "teams")
public class Team {

    public Team() {
    }

    public Team(String teamName, String teamTag, String teamRegion, List<Player> players, String hltvUrl) {
        this.teamName = teamName;
        this.teamTag = teamTag;
        this.teamRegion = teamRegion;
        this.players = players;
        this.hltvUrl = hltvUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String teamName;

    private String teamTag;

    private String teamRegion;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    private String hltvUrl;
}
