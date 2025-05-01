package com.hyuse.chatbot.match.service;

import com.hyuse.chatbot.match.model.dto.MatchDTO;
import com.hyuse.chatbot.match.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface MatchServiceInterface {

    void createMatch(MatchDTO matchDTO);
    void updateMatchById(Long id, MatchDTO matchDTO);

    Match getMatchById(Long id);
    Match getMatchByDate(LocalDateTime date);

    Page<Match> listMatchs(Pageable pageable);

    void deleteMatchById(Long id);
}
