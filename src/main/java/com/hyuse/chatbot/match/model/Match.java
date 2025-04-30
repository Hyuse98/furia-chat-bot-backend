package com.hyuse.chatbot.match.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "matchs")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    @Column(name = "date_hour")
    private LocalDateTime dateHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_status")
    private MatchStatus matchStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_result")
    private MatchResults matchResult;

    @Column(name = "match_scoreboard")
    private String matchScoreboard;

    public Match() {
    }

    public Match(String matchScoreboard, MatchResults matchResult, MatchStatus matchStatus, LocalDateTime dateHour, String teamB, String teamA) {
        this.matchScoreboard = matchScoreboard;
        this.matchResult = matchResult;
        this.matchStatus = matchStatus;
        this.dateHour = dateHour;
        this.teamB = teamB;
        this.teamA = teamA;
    }
}

