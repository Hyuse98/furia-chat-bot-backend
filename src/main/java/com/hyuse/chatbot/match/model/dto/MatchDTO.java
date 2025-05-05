package com.hyuse.chatbot.match.model.dto;

import com.hyuse.chatbot.map.model.Map;
import com.hyuse.chatbot.match.model.MatchResults;
import com.hyuse.chatbot.match.model.MatchStatus;
import com.hyuse.chatbot.team.model.Team;

import java.time.LocalDateTime;
import java.util.List;

public record MatchDTO(

        Team teamA_Team,
        Team teamB_Team,
        LocalDateTime dateHour,
        MatchStatus matchStatus,
        Integer teamAScore,
        Integer teamBScore,
        MatchResults matchResult,
        List<Map> mapPool
) {
}
