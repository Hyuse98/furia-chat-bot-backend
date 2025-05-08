package com.hyuse.chatbot.team.mapper;

import com.hyuse.chatbot.team.model.Team;
import com.hyuse.chatbot.team.model.dto.TeamDTO;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team toEntity (TeamDTO teamDTO){

        return new Team(
                teamDTO.teamName(),
                teamDTO.teamTag(),
                teamDTO.teamRegion(),
                teamDTO.players(),
                teamDTO.hltvUrl()
        );
    }

    public TeamDTO toDto (Team team){

        return new TeamDTO(
                team.getTeamName(),
                team.getTeamTag(),
                team.getTeamRegion(),
                team.getPlayers(),
                team.getHltvUrl()
        );
    }
}
