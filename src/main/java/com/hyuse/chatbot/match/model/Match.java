package com.hyuse.chatbot.match.model;

import com.hyuse.chatbot.map.model.Map;
import com.hyuse.chatbot.team.model.Team;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "matches")
public class Match {

    public Match() {
    }

    public Match(Team teamA_Team, Team teamB_Team, LocalDateTime dateHour, MatchStatus matchStatus,
                 Integer teamAScore, Integer teamBScore, MatchResults matchResult, List<Map> mapPool) {
        this.teamA_Team = teamA_Team;
        this.teamB_Team = teamB_Team;
        this.dateHour = dateHour;
        this.matchStatus = matchStatus;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
        this.matchResult = matchResult;
        this.mapPool = mapPool;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_a_id", nullable = false)
    private Team teamA_Team;

    @ManyToOne
    @JoinColumn(name = "team_b_id", nullable = false)
    private Team teamB_Team;

    private LocalDateTime dateHour;

    private MatchStatus matchStatus;

    private Integer teamAScore;

    private Integer teamBScore;

    private MatchResults matchResult;

    @ManyToMany
    @JoinTable(
            name = "match_maps",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "map_id")
    )
    private List<Map> mapPool;
}

