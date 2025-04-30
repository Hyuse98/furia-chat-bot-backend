package com.hyuse.chatbot.team.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "team_tag")
    private String teamTag;

    @Column(name = "team_country")
    private String teamCountry;

    public Team() {
    }

    public Team(String teamName, String teamTag, String teamCountry) {
        this.teamName = teamName;
        this.teamTag = teamTag;
        this.teamCountry = teamCountry;
    }
}
