package com.hyuse.chatbot.map.model;

import com.hyuse.chatbot.match.model.Match;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "maps")
public class Map {

    public Map() {
    }

    public Map(String mapName, Integer teamAMapScore, Integer teamBMapScore, List<Match> matches) {
        this.mapName = mapName;
        this.teamAMapScore = teamAMapScore;
        this.teamBMapScore = teamBMapScore;
        this.matches = matches;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String mapName;

    private Integer teamAMapScore;

    private Integer teamBMapScore;

    @ManyToMany(mappedBy = "mapPool")
    private List<Match> matches;
}