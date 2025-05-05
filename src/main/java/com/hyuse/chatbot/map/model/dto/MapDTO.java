package com.hyuse.chatbot.map.model.dto;

import com.hyuse.chatbot.match.model.Match;

import java.util.List;

public record MapDTO(
        String mapName,
        Integer teamAMapScore,
        Integer teamBMapScore,
        List<Match> matches
) {
}
