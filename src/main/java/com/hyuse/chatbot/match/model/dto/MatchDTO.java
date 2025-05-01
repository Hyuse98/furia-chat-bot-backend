package com.hyuse.chatbot.match.model.dto;

import com.hyuse.chatbot.match.model.MatchResults;
import com.hyuse.chatbot.match.model.MatchStatus;

import java.time.LocalDateTime;

public record MatchDTO(

        LocalDateTime dateHour,

        String teamA,

        String teamB,

        MatchStatus matchStatus,

        MatchResults matchResult,

        String matchScoreboard
) {
}
