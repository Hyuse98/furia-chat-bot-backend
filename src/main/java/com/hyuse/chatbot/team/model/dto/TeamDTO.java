package com.hyuse.chatbot.team.model.dto;

import com.hyuse.chatbot.player.model.Player;

import java.util.List;

public record TeamDTO (

        String teamName,
        String teamTag,
        String teamRegion,
        List<Player> players,
        String hltvUrl
){
}
