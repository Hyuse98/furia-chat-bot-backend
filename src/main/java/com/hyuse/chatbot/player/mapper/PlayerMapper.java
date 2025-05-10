package com.hyuse.chatbot.player.mapper;

import com.hyuse.chatbot.player.model.Player;
import com.hyuse.chatbot.player.model.dto.PlayerDTO;
import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.model.dto.TeamDTO;

public class PlayerMapper {

    public PlayerDTO toDto(Player player) {
        return new PlayerDTO(
                player.getName(),
                player.getTeam(),
                player.getUsername(),
                player.getHltvUrl()
        );
    }

    public Player toEntity(PlayerDTO playerDTO) {
        return new Player(
                playerDTO.name(),
                playerDTO.username(),
                playerDTO.team(),
                playerDTO.hltvUrl()
        );
    }
}
