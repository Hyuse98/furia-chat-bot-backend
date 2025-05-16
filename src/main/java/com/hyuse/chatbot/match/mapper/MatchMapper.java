package com.hyuse.chatbot.match.mapper;

import com.hyuse.chatbot.match.model.Match;
import com.hyuse.chatbot.match.model.dto.MatchDTO;

public class MatchMapper {

    public Match toEntity(MatchDTO matchDTO) {

        return new Match(
                matchDTO.teamA_Team(),
                matchDTO.teamB_Team(),
                matchDTO.dateHour(),
                matchDTO.matchStatus(),
                matchDTO.teamAScore(),
                matchDTO.teamBScore(),
                matchDTO.matchResult(),
                matchDTO.mapPool()
        );
    }

    public MatchDTO toDto(Match match) {

        return new MatchDTO(
                match.getTeamA_Team(),
                match.getTeamB_Team(),
                match.getDateHour(),
                match.getMatchStatus(),
                match.getTeamAScore(),
                match.getTeamBScore(),
                match.getMatchResult(),
                match.getMapPool()
        );
    }
}
